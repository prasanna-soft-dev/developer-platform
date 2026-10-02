import { useState } from "react";

import { useProblems } from "../hooks/useProblems";
import { useCompanies } from "../hooks/useCompanies";
import { useTopics } from "../hooks/useTopics";

import ProblemTable from "../components/problem/ProblemTable";
import CompanyForm from "../components/company/CompanyForm";
import TopicForm from "../components/topic/TopicForm";
import ProblemForm from "../components/problem/ProblemForm";
import {
    createProblem,
    updateProblem,
    deleteProblem,
} from "../services/problemService";
import ConfirmModal from "../components/common/ConfirmModal";

import { difficulties, statuses } from "../constants/problemOptions";

import { createCompany } from "../services/companyService";
import { createTopic } from "../services/topicService";

import "./Dashboard.css";

const Dashboard = () => {
    // Modal state
    const [showCompanyForm, setShowCompanyForm] = useState(false);
    const [showTopicForm, setShowTopicForm] = useState(false);
    const [showProblemForm, setShowProblemForm] = useState(false);
    const [selectedProblem, setSelectedProblem] = useState(null);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [problemToDelete, setProblemToDelete] = useState(null);
    const [deleting, setDeleting] = useState(false);

    // Problems
    const {
        problems,
        loading,
        error,
        fetchProblems
    } = useProblems();

    // Companies
    const {
        companies,
        fetchCompanies
    } = useCompanies();

    // Topics
    const {
        topics,
        fetchTopics
    } = useTopics();

    const handleEditProblem = (problem) => {
    setSelectedProblem(problem);
    setShowProblemForm(true);
    };

    const handleAddProblem = () => {
    setSelectedProblem(null);
    setShowProblemForm(true);
    };

    const handleDeleteProblem = (problem) => {
    setProblemToDelete(problem);
    setShowDeleteModal(true);
    };

    const handleConfirmDelete = async () => {
    if (!problemToDelete) {
        return;
    }

    try {
        setDeleting(true);

        await deleteProblem(problemToDelete.id);

        await fetchProblems();

        setShowDeleteModal(false);
        setProblemToDelete(null);

    } catch (error) {
        console.error(
            "Failed to delete problem:",
            error
        );

        alert(
            error?.response?.data?.message ||
            "Failed to delete problem. Please try again."
        );

    } finally {
        setDeleting(false);
    }
};


    // Loading state
    if (loading) {
        return (
            <main className="page">
                <div className="loading">
                    Loading problems...
                </div>
            </main>
        );
    }

    // Error state
    if (error) {
        return (
            <main className="page">
                <div className="error-message">
                    {error}
                </div>
            </main>
        );
    }

    // Statistics
    const solvedCount = problems.filter(
        (problem) =>
            problem.problemStatus === "SOLVED_WITHOUT_HELP" ||
            problem.problemStatus === "SOLVED_WITH_HELP"
    ).length;

    const yetToSolveCount = problems.filter(
        (problem) =>
            problem.problemStatus === "YET_TO_SOLVE"
    ).length;

    return (
        <main className="page">

            {/* Header */}

            <header className="dashboard-header">

                <div className="brand-section">

                    <div className="brand-icon">
                        D
                    </div>

                    <div>
                        <h1>DSA Tracker</h1>
                        <p>Track your coding journey</p>
                    </div>

                </div>

            </header>


            {/* Statistics */}

            <section className="stats-grid">

                <div className="stat-card">

                    <div className="stat-card-header">
                        <span>Total Problems</span>
                        <span className="stat-icon">
                            Σ
                        </span>
                    </div>

                    <strong>
                        {problems.length}
                    </strong>

                    <p>
                        Problems tracked
                    </p>

                </div>


                <div className="stat-card">

                    <div className="stat-card-header">
                        <span>Solved</span>

                        <span className="stat-icon solved">
                            ✓
                        </span>
                    </div>

                    <strong>
                        {solvedCount}
                    </strong>

                    <p>
                        {problems.length > 0
                            ? Math.round(
                                (solvedCount / problems.length) * 100
                            )
                            : 0}
                        % of total
                    </p>

                </div>


                <div className="stat-card">

                    <div className="stat-card-header">
                        <span>Yet to Solve</span>

                        <span className="stat-icon pending">
                            ○
                        </span>
                    </div>

                    <strong>
                        {yetToSolveCount}
                    </strong>

                    <p>
                        Keep going
                    </p>

                </div>

            </section>


            {/* Problems */}

            <section className="problems-section">

                <div className="section-header">

                    <div>
                        <h2>
                            Problems
                        </h2>

                        <p>
                            Manage and track your DSA problems
                        </p>
                    </div>


                    {/* Header Actions */}

                    <div className="header-actions">

                        <button
                            className="secondary-button"
                            onClick={() =>
                                setShowCompanyForm(true)
                            }
                        >
                            + Company
                        </button>


                        <button
                            className="secondary-button"
                            onClick={() =>
                                setShowTopicForm(true)
                            }
                        >
                            + Topic
                        </button>


                        <button
                            className="primary-button" onClick={handleAddProblem}
                        >
                            + Add Problem
                        </button>

                    </div>

                </div>


                {/* Filters */}

                <div className="filters">

                    {/* Search */}

                    <div className="search-wrapper">

                        <span className="search-icon">
                            ⌕
                        </span>

                        <input
                            type="text"
                            placeholder="Search problems..."
                        />

                    </div>


                    {/* Company */}

                    <select defaultValue="">

                        <option value="">
                            All Companies
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


                    {/* Topic */}

                    <select defaultValue="">

                        <option value="">
                            All Topics
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


                    {/* Difficulty */}

                    <select defaultValue="">

                        <option value="">
                            All Difficulties
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


                    {/* Status */}

                    <select defaultValue="">

                        <option value="">
                            All Status
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

                </div>


                {/* Problem Table */}

                <div className="table-card">

                    <ProblemTable
                        problems={problems}
                        onEdit={handleEditProblem}
                        onDelete={handleDeleteProblem}
                    />

                </div>

            </section>


            {/* Company Form */}

            <CompanyForm
                isOpen={showCompanyForm}

                onClose={() =>
                    setShowCompanyForm(false)
                }

                onSubmit={async (companyName) => {

                    await createCompany(companyName);

                    // Refresh company dropdown
                    await fetchCompanies();

                }}
            />


            {/* Topic Form */}

            <TopicForm
                isOpen={showTopicForm}

                onClose={() =>
                    setShowTopicForm(false)
                }

                onSubmit={async (topicName) => {

                    await createTopic(topicName);

                    // Refresh topic dropdown
                    await fetchTopics();

                }}
            />

            {/* Problem Form */}
            <ProblemForm
                isOpen={showProblemForm}
                onClose={() => {
                    setShowProblemForm(false);
                    setSelectedProblem(null);
                }}
                companies={companies}
                topics={topics}
                problem={selectedProblem}
                onSubmit={async (problemData) => {

                    if (selectedProblem) {
                        await updateProblem(
                            selectedProblem.id,
                            problemData
                        );
                    } else {
                        await createProblem(problemData);
                    }

                    await fetchProblems();
                }}
            />
            <ConfirmModal
                isOpen={showDeleteModal}
                onClose={() => {
                    if (deleting) {
                        return;
                    }

                    setShowDeleteModal(false);
                    setProblemToDelete(null);
                }}
                onConfirm={handleConfirmDelete}
                title="Delete Problem"
                message={
                    problemToDelete
                        ? `Are you sure you want to delete "${problemToDelete.problemTitle}"? This action cannot be undone.`
                        : ""
                }
                confirmText="Delete"
                cancelText="Cancel"
                loading={deleting}
            />

        </main>
    );
};

export default Dashboard;