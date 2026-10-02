import api from "./api";

export const getProblems = () => {
    return api.get("/problem");
};

export const createProblem = (problem) => {
    return api.post("/problem", problem);
};

export const updateProblem = (id, problem) => {
    return api.put(`/problem/${id}`, problem);
};

export const deleteProblem = (id) => {
    return api.delete(`/problem/${id}`);
};