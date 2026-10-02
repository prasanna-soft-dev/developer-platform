import api from "./api";

export const getTopics = () => {
    return api.get("/topic");
}

export const createTopic = (topicName) => {
    return api.post("/topic", null, {
        params: {
            topicName
        }
    });
};

export const updateTopic = (id, topic) => {
    return api.put(`/topic/${id}`, topic);
}

export const deleteTopic = (id) => {
    return api.delete(`/topic/${id}`);
}