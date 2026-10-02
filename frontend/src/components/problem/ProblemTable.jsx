import "./ProblemTable.css";

const ProblemTable = ({
    problems,
    onEdit,
    onDelete,
}) => {
    return (
        <div className="table-wrapper">

            <table className="problem-table">

                <thead>
                    <tr>
                        <th>Company</th>
                        <th>Problem</th>
                        <th>Platform</th>
                        <th>Link</th>
                        <th>Topic</th>
                        <th>Difficulty</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>

                    {problems.map((problem) => (

                        <tr key={problem.id}>

                            <td>
                                <span className="company-cell">
                                    {problem.companyId}
                                </span>
                            </td>

                            <td>
                                <span className="problem-title">
                                    {problem.problemTitle}
                                </span>
                            </td>

                            <td>
                                {problem.platformName}
                            </td>

                            <td>
                                {problem.problemLink ? (
                                    <a
                                        className="problem-link"
                                        href={problem.problemLink}
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        Open ↗
                                    </a>
                                ) : (
                                    <span className="text-muted">
                                        —
                                    </span>
                                )}
                            </td>

                            <td>
                                {problem.topicId}
                            </td>

                            <td>

                                <span
                                    className={`difficulty difficulty-${problem.difficulty.toLowerCase()}`}
                                >
                                    {problem.difficulty}
                                </span>

                            </td>

                            <td>

                                <span
                                    className={`status status-${problem.problemStatus
                                        .toLowerCase()
                                        .replaceAll("_", "-")}`}
                                >
                                    {problem.problemStatus.replaceAll(
                                        "_",
                                        " "
                                    )}
                                </span>

                            </td>

                            <td>

                                <div className="action-buttons">

                                    <button
                                        className="table-action"
                                        onClick={() =>
                                            onEdit(problem)
                                        }
                                    >
                                        Edit
                                    </button>

                                    <button
                                        className="table-action delete-action"
                                        onClick={() =>
                                            onDelete(problem)
                                        }
                                    >
                                        Delete
                                    </button>

                                </div>

                            </td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </div>
    );
};

export default ProblemTable;