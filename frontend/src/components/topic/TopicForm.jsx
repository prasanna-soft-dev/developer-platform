import { useState } from "react";
import Modal from "../common/Modal";
import TextInput from "../common/TextInput";
import "./TopicForm.css";

const TopicForm = ({ isOpen, onClose, onSubmit }) => {
    const [topicName, setTopicName] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();

        const trimmedName = topicName.trim();

        if (!trimmedName) {
            setError("Topic name is required.");
            return;
        }

        try {
            await onSubmit(trimmedName);

            setTopicName("");
            setError("");
            onClose();
        } catch (error) {
            setError(
                error?.response?.data?.message ||
                "Failed to create topic."
            );
        }
    };

    const handleClose = () => {
        setTopicName("");
        setError("");
        onClose();
    };

    return (
        <Modal
            isOpen={isOpen}
            onClose={handleClose}
            title="Add Topic"
        >
            <form onSubmit={handleSubmit}>
                <TextInput
                    label="Topic Name"
                    value={topicName}
                    onChange={(event) => {
                        setTopicName(event.target.value);
                        setError("");
                    }}
                    placeholder="e.g. Dynamic Programming"
                    error={error}
                    required
                />

                <div className="form-actions">
                    <button
                        type="button"
                        className="secondary-button"
                        onClick={handleClose}
                    >
                        Cancel
                    </button>

                    <button
                        type="submit"
                        className="primary-button"
                    >
                        Add Topic
                    </button>
                </div>
            </form>
        </Modal>
    );
};

export default TopicForm;