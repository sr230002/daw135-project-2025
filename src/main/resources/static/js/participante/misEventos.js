function marcarAsistencia(eventoRegistroId, eventoId) {
    fetch(`/participante/marcarAsistencia/${eventoRegistroId}`, {
        method: 'POST',
    })
    .then(response => {
        if (response.ok) {
            window.location.href = `/participante/detalleEvento/${eventoId}`;
        } else {
            mostrarToast('Error al marcar asistencia', 'danger');
        }
    })
    .catch(error => console.error('Error:', error));
}