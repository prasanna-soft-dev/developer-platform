import { useEffect, useState } from "react";

import Modal from "../common/Modal";
import TextInput from "../common/TextInput";

import { difficulties, statuses } from "../../constants/problemOptions";

import "./ProblemForm.css";

const initialForm = {
    problemTitle: "",
    problemLink: "",
    platformName: "",
    companyId: "",
    topicId: "",
    difficulty: "",
    problemStatus: "",
    notes: "",
};

const ProblemForm = ({
    isOpen,
    onClose,
    onSubmit,
    companies,
    topics,
    problem,
}) => {
    const [form, setForm] = useState(initialForm);
    const [errors, setErrors] = useState({});
    const [submitting, setSubmitting] = useState(false);

    const isEditMode = Boolean(problem);

    useEffect(() => {
        if (!isOpen) {
            return;
        }

        if (problem) {
            setForm({
                problemTitle: problem.problemTitle ?? "",
                problemLink: problem.problemLink ?? "",
                platformName: problem.platformName ?? "",
                companyId: problem.companyId?.toString() ?? "",
                topicId: problem.topicId?.toString() ?? "",
                difficulty: problem.difficulty ?? "",
                problemStatus: problem.problemStatus ?? "",
                notes: problem.notes ?? "",
            });
        } else {
            setForm(initialForm);
        }

        setErrors({});
    }, [isOpen, problem]);

    const handleChange = (event) => {
        const { name, value } = event.target;

        setForm((previous) => ({
            ...previous,
            [name]: value,
        }));

        setErrors((previous) => ({
            ...previous,
            [name]: "",
            submit: "",
        }));
    };

    const validate = () => {
        const newErrors = {};

        if (!form.problemTitle.trim()) {
            newErrors.problemTitle = "Problem title is required.";
        }

        if (!form.platformName.trim()) {
            newErrors.platformName = "Platform is required.";
        }

        if (!form.companyId) {
            newErrors.companyId = "Please select a company.";
        }

        if (!form.topicId) {
            newErrors.topicId = "Please select a topic.";
        }

        if (!form.difficulty) {
            newErrors.difficulty = "Please select a difficulty.";
        }

        if (!form.problemStatus) {
            newErrors.problemStatus = "Please select a status.";
        }

        if (
            form.problemLink.trim() &&
            !/^https?:\/\/.+/i.test(form.problemLink.trim())
        ) {
            newErrors.problemLink =
                "Please enter a valid URL starting with http:// or https://.";
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!validate()) {
            return;
        }

        const problemData = {
            problemTitle: form.problemTitle.trim(),
            problemLink: form.problemLink.trim() || null,
            platformName: form.platformName.trim(),
            companyId: Number(form.companyId),
            topicId: Number(form.topicId),
            difficulty: form.difficulty,
            problemStatus: form.problemStatus,
            notes: form.notes.trim() || null,
        };

        try {
            setSubmitting(true);

            await onSubmit(problemData);

            setForm(initialForm);
            setErrors({});
            onClose();
        } catch (error) {
            setErrors({
                submit:
                    error?.response?.data?.message ||
                    `Failed to ${
                        isEditMode ? "update" : "create"
                    } problem. Please try again.`,
            });
        } finally {
            setSubmitting(false);
        }
    };

    const handleClose = () => {
        if (submitting) {
            return;
        }

        setForm(initialForm);
        setErrors({});
        onClose();
    };

    return (
        <Modal
            isOpen={isOpen}
            onClose={handleClose}
            title={isEditMode ? "Edit Problem" : "Add Problem"}
        >
            <form
                className="problem-form"
                onSubmit={handleSubmit}
            >

                {errors.submit && (
                    <div className="form-error">
                        {errors.submit}
                    </div>
                )}

                {/* Problem Title */}

                <TextInput
                    label="Problem Title"
                    name="problemTitle"
                    value={form.problemTitle}
                    onChange={handleChange}
                    placeholder="e.g. Two Sum"
                    error={errors.problemTitle}
                    required
                />

                {/* Problem Link */}

                <TextInput
                    label="Problem Link"
                    name="problemLink"
                    value={form.problemLink}
                    onChange={handleChange}
                    placeholder="https://leetcode.com/problems/two-sum/"
                    error={errors.problemLink}
                />

                {/* Platform */}

                <TextInput
                    label="Platform"
                    name="platformName"
                    value={form.platformName}
                    onChange={handleChange}
                    placeholder="e.g. LeetCode"
                    error={errors.platformName}
                    required
                />

                {/* Company + Topic */}

                <div className="form-row">

                    <div className="form-field">

                        <label>
                            Company
                            <span className="required-mark">
                                *
                            </span>
                        </label>

                        <select
                            name="companyId"
                            value={form.companyId}
                            onChange={handleChange}
                            className={
                                errors.companyId
                                    ? "input-error"
                                    : ""
                            }
                        >
                            <option value="">
                                Select company
                            </option>

                            {companies.map((company) => (
                                <option
                                    key={company.id}
                                    value={company.id}
                                >
                                    {company.companyName}
                                </option>
                            ))}
                        </select>

                        {errors.companyId && (
                            <span className="field-error">
                                {errors.companyId}
                            </span>
                        )}

                    </div>

                    <div className="form-field">

                        <label>
                            Topic
                            <span className="required-mark">
                                *
                            </span>
                        </label>

                        <select
                            name="topicId"
                            value={form.topicId}
                            onChange={handleChange}
                            className={
                                errors.topicId
                                    ? "input-error"
                                    : ""
                            }
                        >
                            <option value="">
                                Select topic
                            </option>

                            {topics.map((topic) => (
                                <option
                                    key={topic.id}
                                    value={topic.id}
                                >
                                    {topic.topicName}
                                </option>
                            ))}
                        </select>

                        {errors.topicId && (
                            <span className="field-error">
                                {errors.topicId}
                            </span>
                        )}

                    </div>

                </div>

                {/* Difficulty + Status */}

                <div className="form-row">

                    <div className="form-field">

                        <label>
                            Difficulty
                            <span className="required-mark">
                                *
                            </span>
                        </label>

                        <select
                            name="difficulty"
                            value={form.difficulty}
                            onChange={handleChange}
                            className={
                                errors.difficulty
                                    ? "input-error"
                                    : ""
                            }
                        >
                            <option value="">
                                Select difficulty
                            </option>

                            {difficulties.map((difficulty) => (
                                <option
                                    key={difficulty.value}
                                    value={difficulty.value}
                                >
                                    {difficulty.label}
                                </option>
                            ))}
                        </select>

                        {errors.difficulty && (
                            <span className="field-error">
                                {errors.difficulty}
                            </span>
                        )}

                    </div>

                    <div className="form-field">

                        <label>
                            Status
                            <span className="required-mark">
                                *
                            </span>
                        </label>

                        <select
                            name="problemStatus"
                            value={form.problemStatus}
                            onChange={handleChange}
                            className={
                                errors.problemStatus
                                    ? "input-error"
                                    : ""
                            }
                        >
                            <option value="">
                                Select status
                            </option>

                            {statuses.map((status) => (
                                <option
                                    key={status.value}
                                    value={status.value}
                                >
                                    {status.label}
                                </option>
                            ))}
                        </select>

                        {errors.problemStatus && (
                            <span className="field-error">
                                {errors.problemStatus}
                            </span>
                        )}

                    </div>

                </div>

                {/* Notes */}

                <div className="form-field">

                    <label>
                        Notes
                    </label>

                    <textarea
                        name="notes"
                        value={form.notes}
                        onChange={handleChange}
                        placeholder="Add your notes, approach, mistakes, or things to remember..."
                        rows="4"
                    />

                </div>

                {/* Actions */}

                <div className="form-actions">

                    <button
                        type="button"
                        className="secondary-button"
                        onClick={handleClose}
                        disabled={submitting}
                    >
                        Cancel
                    </button>

                    <button
                        type="submit"
                        className="primary-button"
                        disabled={submitting}
                    >
                        {submitting
                            ? isEditMode
                                ? "Saving..."
                                : "Adding..."
                            : isEditMode
                                ? "Save Changes"
                                : "Add Problem"}
                    </button>

                </div>

            </form>
        </Modal>
    );
};

export default ProblemForm;