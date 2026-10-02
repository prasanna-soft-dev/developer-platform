import { useState } from "react";
import Modal from "../common/Modal";
import TextInput from "../common/TextInput";
import "./CompanyForm.css";

const CompanyForm = ({ isOpen, onClose, onSubmit }) => {
    const [companyName, setCompanyName] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();

        const trimmedName = companyName.trim();

        if (!trimmedName) {
            setError("Company name is required.");
            return;
        }

        try {
            await onSubmit(trimmedName);

            setCompanyName("");
            setError("");
            onClose();
        } catch (error) {
            setError(
                error?.response?.data?.message ||
                "Failed to create company."
            );
        }
    };

    const handleClose = () => {
        setCompanyName("");
        setError("");
        onClose();
    };

    return (
        <Modal
            isOpen={isOpen}
            onClose={handleClose}
            title="Add Company"
        >
            <form onSubmit={handleSubmit}>
                <TextInput
                    label="Company Name"
                    value={companyName}
                    onChange={(event) => {
                        setCompanyName(event.target.value);
                        setError("");
                    }}
                    placeholder="e.g. Google"
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
                        Add Company
                    </button>
                </div>
            </form>
        </Modal>
    );
};

export default CompanyForm;