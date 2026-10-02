import Modal from "./Modal";
import "./ConfirmModal.css";

const ConfirmModal = ({
    isOpen,
    onClose,
    onConfirm,
    title = "Confirm Action",
    message,
    confirmText = "Confirm",
    cancelText = "Cancel",
    loading = false,
}) => {
    return (
        <Modal
            isOpen={isOpen}
            onClose={loading ? () => {} : onClose}
            title={title}
        >
            <div className="confirm-modal">

                <div className="confirm-icon">
                    !
                </div>

                <p className="confirm-message">
                    {message}
                </p>

                <div className="confirm-actions">

                    <button
                        type="button"
                        className="secondary-button"
                        onClick={onClose}
                        disabled={loading}
                    >
                        {cancelText}
                    </button>

                    <button
                        type="button"
                        className="danger-button"
                        onClick={onConfirm}
                        disabled={loading}
                    >
                        {loading ? "Deleting..." : confirmText}
                    </button>

                </div>

            </div>
        </Modal>
    );
};

export default ConfirmModal;