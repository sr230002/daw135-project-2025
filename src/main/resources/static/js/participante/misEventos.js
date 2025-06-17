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

function marcarAsistenciaAdm(eventoRegistroId, estado) {
    fetch(`/participante/marcarAsistenciaAdm/${eventoRegistroId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ estado: estado })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Actualizar badge de asistencia
                const row = document.querySelector(`#btn-group-${eventoRegistroId}`).closest('tr');
                const badge = row.querySelector('td:nth-child(3) span');

                if (data.asistenciaConfirmada) {
                    badge.className = 'badge bg-success';
                    badge.textContent = 'Si';
                } else {
                    badge.className = 'badge bg-secondary';
                    badge.textContent = 'No';
                }

                // Actualizar botones
                const btnGroup = document.querySelector(`#btn-group-${eventoRegistroId}`);
                btnGroup.innerHTML = '';

                if (data.asistenciaConfirmada) {
                    // Mostrar botón para quitar asistencia
                    btnGroup.innerHTML = `
                    <a class="btn btn-outline-danger" onclick="marcarAsistenciaAdm(${eventoRegistroId}, false)">
                        <i class="bi bi-x-circle"></i>
                    </a>`;
                } else {
                    // Mostrar botón para marcar asistencia
                    btnGroup.innerHTML = `
                    <a class="btn btn-outline-success" onclick="marcarAsistenciaAdm(${eventoRegistroId}, true)">
                        <i class="bi bi-check2"></i>
                    </a>`;
                }
            } else {
                console.error('Error al actualizar asistencia:', data.message);
                alert(data.message);
            }
        })
        .catch(error => console.error('Error:', error));
}

async function subirAdjuntoEvento(eventoId, file, descripcion = '', visible = true) {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('eventoId', eventoId);
    formData.append('descripcion', descripcion);
    formData.append('visible', visible.toString());

    try {
        const response = await fetch(`/adjuntos/evento/subir`, {
            method: 'POST',
            body: formData,
        });

        if (!response.ok) {
            const errorText = await response.text();
            return { ok: false, error: errorText || response.statusText };
        }

        const data = await response.json();
        return { ok: true, data };

    } catch (error) {
        console.error('Error al subir adjunto:', error);
        return { ok: false, error: error.message };
    }
}

async function eliminarAdjuntoEvento(eventoAdjuntoId) {
    try {
        const response = await fetch(`/adjuntos/evento/eliminar/${eventoAdjuntoId}`, {
            method: 'DELETE',
        });

        if (!response.ok) {
            const errorText = await response.text();
            return { ok: false, error: errorText || response.statusText };
        }

        return { ok: true, data: null };

    } catch (error) {
        console.error('Error al eliminar adjunto:', error);
        return { ok: false, error: error.message };
    }
}

async function subirAdjuntoModal(eventoId) {
    // Obtener elementos del DOM
    const fileInput = document.getElementById('attachmentFile');
    const descripcion = document.getElementById('attachmentDescription').value;
    const visible = document.getElementById('attachmentVisible').checked;
    const submitBtn = document.getElementById('submitAttachment');

    if (!fileInput.files || fileInput.files.length === 0) {
        mostrarToast('Seleccione un archivo', 'danger');
        return;
    }

    submitBtn.disabled = true;
    submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status"></span> Subiendo...';

    try {
        const resultado = await subirAdjuntoEvento(
            eventoId,
            fileInput.files[0],
            descripcion,
            visible
        );

        if (resultado.ok) {
            mostrarToast('Adjunto subido con éxito', 'success');
            bootstrap.Modal.getInstance(document.getElementById('addAttachmentModal')).hide();
        } else {
            mostrarToast(resultado.error || 'Error al subir archivo', 'danger');
        }
    } catch (error) {
        console.error('Error:', error);
        mostrarToast('Error inesperado al subir archivo', 'danger');
    } finally {
        submitBtn.disabled = false;
        submitBtn.innerHTML = 'Subir';
    }
}

async function eliminarAdjuntoModal(eventoAdjuntoId) {
    const confirmBtn = document.getElementById('confirmAdjuntoDeletion');
    confirmBtn.disabled = true;
    confirmBtn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status"></span> Eliminando...';

    try {
        const resultado = await eliminarAdjuntoEvento(eventoAdjuntoId);

        if (resultado.ok) {
            mostrarToast('Adjunto eliminado con éxito', 'success');
        } else {
            mostrarToast(resultado.error || 'Error al eliminar adjunto', 'danger');
        }
    } catch (error) {
        console.error('Error:', error);
        mostrarToast('Error inesperado al eliminar adjunto', 'danger');
    } finally {
        confirmBtn.disabled = false;
        confirmBtn.innerHTML = 'Eliminar';
    }
}

function abrirArchivo(element) {
    const url = element.getAttribute('data-url');
    const nombre = element.getAttribute('data-nombre');
    const tipo = element.getAttribute('data-tipo');

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener el archivo');
            }
            return response.blob();
        })
        .then(blob => {
            const blobUrl = URL.createObjectURL(blob);
            // Forzar descarga
            const link = document.createElement('a');
            link.href = blobUrl;
            link.download = nombre;  // ✅ nombre completo con extensión
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);

        })
        .catch(error => {
            console.error('Error:', error);
            alert('No se pudo abrir el archivo');
        });
}