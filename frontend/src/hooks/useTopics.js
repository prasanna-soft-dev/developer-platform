import { useEffect, useState } from "react";
import { getTopics } from "../services/topicService";

export const useTopics = () => {
    const [topics, setTopics] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchTopics = async () => {
        try {
            setLoading(true);

            const response = await getTopics();

            setTopics(response.data.data);
        } catch (error) {
            console.error("Failed to fetch topics:", error);
            setError("Failed to fetch topics");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchTopics();
    }, []);

    return {
        topics,
        loading,
        error,
        fetchTopics
    };
};