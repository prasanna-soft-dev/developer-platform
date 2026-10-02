import api from "./api";

export const getCompanies = () => {
    return api.get("/company");
}

export const createCompany = (companyName) => {
    return api.post("/company", null, {
        params: {
            companyName
        }
    });
};

export const updateCompany = (id, company) => {
    return api.put(`/company/${id}`, company);
}

export const deleteCompany = (id) => {
    return api.delete(`/company/${id}`);
}