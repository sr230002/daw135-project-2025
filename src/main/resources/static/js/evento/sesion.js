let sesionModal;

function mostrarFormulario() {
    document.getElementById('sesionForm').reset();
    document.getElementById('sesionModalLabel').textContent = "Nuevo Evento";
    sesionModal.show();
}

function cargarEvento(eventoId) {
    showLoading('Cargando Evento...');
    fetch(`sesiones/ver/${eventoId}`)
        .then(response => response.json())
        .then(evento => {
            //hideLoading();
            document.getElementById("sesionForm").action =  `${basePath}sesiones/editar`;

            document.getElementById("eventoId").value = evento.eventoId;
            document.getElementById("codigo").value = evento.codigo;
            document.getElementById("fechaCreacion").value = evento.fechaCreacion;
            document.getElementById("fechaInicio").value = evento.fechaInicio;
            document.getElementById("fechaFin").value = evento.fechaFin;
            document.getElementById("titulo").value = evento.titulo;
            document.getElementById("descripcion").value = evento.descripcion;
            document.getElementById("descripcionCorta").value = evento.descripcionCorta;
            document.getElementById("tipoEventoId").value = evento.tipoEventoId;
            document.getElementById("sedeId").value = evento.sedeId;
            document.getElementById("estadoId").value = evento.estadoId;
            
            let modal = new bootstrap.Modal(document.getElementById("sesionModal"));
            modal.show();
            hideLoading();
        })
        .catch(error =>{
            hideLoading();
            console.error('Error al cargar el evento:', error);
        });
}


document.addEventListener('DOMContentLoaded', () => {
    sesionModal = new bootstrap.Modal(document.getElementById('sesionModal'));
});

document.getElementById('sesionForm').addEventListener('submit', (e) => {
    showLoading();
});