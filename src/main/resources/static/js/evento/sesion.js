let sesionModal;

function mostrarFormulario() {
    document.getElementById('sesionForm').reset();
    document.getElementById('sesionModalLabel').textContent = "Nuevo Evento";
    sesionModal.show();
}

function cargarSesion (eventoProgrmacionId) {
    showLoading('Cargando Sesion...');
    fetch(`sesiones/ver/${eventoProgrmacionId}`)
        .then(response => response.json())
        .then(sesion => {
            document.getElementById("sesionForm").action =  `${basePath}sesiones/editar`;

            document.getElementById("eventoProgramacionId").value = sesion.eventoProgramacionId;
            document.getElementById("eventoId").value = sesion.eventoId;
            document.getElementById("ponenteId").value = sesion.ponenteId;
            document.getElementById("fechaProgramacion").value = sesion.fechaProgramacion;
            document.getElementById("horaInicio").value = sesion.horaInicio;
            document.getElementById("horaFin").value = sesion.horaFin;
            document.getElementById("virtualSi").checked = sesion.virtual;
            document.getElementById("virtualNo").checked = !sesion.virtual;
            document.getElementById("cupos").value = sesion.cupos;
            document.getElementById("lugar").value = sesion.lugar ?? '';
            document.getElementById("enlace").value = sesion.enlace ?? '';
            
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