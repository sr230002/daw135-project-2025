let participanteModal;

function mostrarFormulario() {
    document.getElementById('participanteForm').reset();
    document.getElementById('participanteModalLabel').textContent = 'Nuevo Participante';
    participanteModal.show();
}

function cargarParticipante(registroId) {
    fetch(`registros/ver/${registroId}`)
        .then(response => response.json())
        .then(registro => {
            document.getElementById('participanteForm').action =  `${basePath}registros/editar`;

            document.getElementById('eventoRegistroId').value = registroId;
            document.getElementById('participanteId').value = registro.participanteId;
            document.getElementById('eventoId').value = registro.eventoId;
            
            cargarSesionesPorEvento(registro.eventoId).then(() => {
                document.getElementById('sesionId').value = registro.sesionId;
            });
            
            let modal = new bootstrap.Modal(document.getElementById('participanteModal'));
            modal.show();
        })
        .catch(error => console.error('Error al cargar el evento:', error));
}

function cargarSesionesPorEvento(eventoId) {
    return new Promise((resolve, reject) => { 
        if(eventoId === '') { 
            resolve();
            return; 
        }
        fetch(`sesiones/sesionesPorEventoCmb/${eventoId}`)
            .then(response => response.json())
            .then(sesiones => {
                let combo = document.getElementById('sesionId');
                combo.required = true;       
                while (combo.options.length) {
                    combo.remove(0);
                }
                if (sesiones.length === 0) {
                    combo.disabled = true;
                    mostrarToast('No hay sesiones disponibles para este evento.', 'danger', 1000);
                    resolve();
                    return;
                }
                combo.disabled = false;
                combo.appendChild(new Option('Seleccione una sesión', '0'));
                sesiones.forEach(sesion => {
                    combo.appendChild(new Option(sesion.descripcion, sesion.eventoProgramacionId));
                });
                resolve(); 
            })
            .catch(error => {
                console.error('Error al cargar las sesiones:', error);
                reject(error);
            });
    });
}

document.addEventListener('DOMContentLoaded', () => {
    participanteModal = new bootstrap.Modal(document.getElementById('participanteModal'));
});