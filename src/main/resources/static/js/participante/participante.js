let participanteModal;
let listaDeSesiones = [];

function mostrarFormulario() {
    document.getElementById('participanteForm').reset();
    document.getElementById('participanteModalLabel').textContent = 'Nuevo Participante';
    participanteModal.show();
}

function cargarParticipante(registroId) {
    showLoading('Cargando participante...');
    fetch(`registros/ver/${registroId}`)
        .then(response => response.json())
        .then(registro => {
            //hideLoading();
            document.getElementById('participanteForm').action =  `${basePath}registros/editar`;

            document.getElementById('eventoRegistroId').value = registroId;
            document.getElementById('participanteId').value = registro.participanteId;
            document.getElementById('eventoId').value = registro.eventoId;
            
            cargarSesionesPorEvento(registro.eventoId).then(() => {
                document.getElementById('sesionId').value = registro.sesionId;
                mostrarCupos(registro.sesionId);
            });
            
            let modal = new bootstrap.Modal(document.getElementById('participanteModal'));
            modal.show();
            setTimeout(() => {
                hideLoading();
            }, 1000);
        })
        .catch(error => {
            hideLoading();
            console.error('Error al cargar el evento:', error);
        });
}

function cargarSesionesPorEvento(eventoId) {
    let combo = document.getElementById('sesionId');
    combo.innerHTML = '<option value="">Cargando...</option>';
    return new Promise((resolve) => { 
        if(eventoId === '') { 
            resolve();
            return; 
        }
        fetch(`sesiones/sesionesPorEventoCmb/${eventoId}`)
        .then(response => response.json())
        .then(sesiones => {
            combo.innerHTML = '';
            if (sesiones.length === 0) {
                combo.disabled = true;
                listaDeSesiones = [];
                combo.appendChild(new Option('No hay sesiones', '0'));
                mostrarToast('No hay sesiones disponibles.', 'danger', 1000);
            } else {
                combo.disabled = false;
                listaDeSesiones = sesiones;
                combo.appendChild(new Option('Seleccione una sesión', ''));
                sesiones.forEach(sesion => {
                    combo.appendChild(new Option(sesion.descripcion, sesion.eventoProgramacionId));
                });
            }
            resolve();
        });
    });
}

function mostrarCupos(sesionId) {
    let cupos = document.getElementById('cupos');
    let virtual = document.getElementById('virtual');
    let sesion = listaDeSesiones.find(i => i.eventoProgramacionId === parseInt(sesionId));
    if (sesion ) {
        cupos.value = sesion.cupos;
        virtual.value = sesion.virtual ? 'Virtual' : 'Presencial';
    }else {
        cupos.value = '';
        virtual.value = '';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    participanteModal = new bootstrap.Modal(document.getElementById('participanteModal'));
});

document.getElementById('participanteForm').addEventListener('submit', (e) => {
    const sesionId = document.getElementById('sesionId');
    if (sesionId.value === '' || sesionId.value === '0') {
        e.preventDefault();
        mostrarToast('Debe seleccionar un Evento que tenga al menos una sesión.', 'danger');
        sesionId.focus();
    }
    showLoading();
});