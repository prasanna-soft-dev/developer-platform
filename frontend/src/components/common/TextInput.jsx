import "./TextInput.css";

const TextInput = ({
    label,
    name,
    value,
    onChange,
    placeholder,
    error,
    required = false,
}) => {
    return (
        <div className="form-field">
            <label>
                {label}

                {required && (
                    <span className="required-mark">*</span>
                )}
            </label>

            <input
                type="text"
                name={name}
                value={value}
                onChange={onChange}
                placeholder={placeholder}
                className={error ? "input-error" : ""}
            />

            {error && (
                <span className="field-error">
                    {error}
                </span>
            )}
        </div>
    );
};

export default TextInput;