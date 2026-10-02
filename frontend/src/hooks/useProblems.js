import { useEffect, useState } from "react";
import { getProblems } from "../services/problemService";

export const useProblems = () => {
    const [problems, setProblems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchProblems = async () => {
        try {
            setLoading(true);

            const response = await getProblems();

            setProblems(response.data.data);
        } catch (error) {
            console.error("Failed to fetch problems:", error);
            setError("Failed to fetch problems");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchProblems();
    }, []);

    return {
        problems,
        loading,
        error,
        fetchProblems
    };
};