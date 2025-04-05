let eventoModal;

function mostrarFormulario() {
    document.getElementById('eventoForm').reset();
    document.getElementById('eventoModalLabel').textContent = "Nuevo Evento";
    eventoModal.show();
}

function cargarEvento(eventoId) {
    showLoading('Cargando Evento...');
    fetch(`eventos/ver/${eventoId}`)
        .then(response => response.json())
        .then(evento => {
            hideLoading();
            document.getElementById("eventoForm").action =  `${basePath}eventos/editar`;

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
            
            let modal = new bootstrap.Modal(document.getElementById("eventoModal"));
            modal.show();
        })
        .catch(error =>{
            hideLoading();
            console.error('Error al cargar el evento:', error);
        });
}


document.addEventListener('DOMContentLoaded', () => {
    eventoModal = new bootstrap.Modal(document.getElementById('eventoModal'));
});

document.getElementById('eventoForm').addEventListener('submit', (e) => {
    showLoading();
});