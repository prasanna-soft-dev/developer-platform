import { useEffect, useState } from "react";
import { getCompanies } from "../services/companyService";

export const useCompanies = () => {
    const [companies, setCompanies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchCompanies = async () => {
        try {
            setLoading(true);

            const response = await getCompanies();

            setCompanies(response.data.data);
        } catch (error) {
            console.error("Failed to fetch companies:", error);
            setError("Failed to fetch companies");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchCompanies();
    }, []);

    return {
        companies,
        loading,
        error,
        fetchCompanies
    };
};