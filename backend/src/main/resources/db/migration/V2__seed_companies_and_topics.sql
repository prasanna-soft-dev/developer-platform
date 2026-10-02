-- V2__seed_companies_and_topics.sql

INSERT INTO company (company_name) VALUES
                                       ('Google'),
                                       ('Microsoft'),
                                       ('Amazon'),
                                       ('Meta'),
                                       ('Apple'),
                                       ('Netflix'),
                                       ('Adobe'),
                                       ('Salesforce'),
                                       ('Uber'),
                                       ('Airbnb'),
                                       ('LinkedIn'),
                                       ('Atlassian'),
                                       ('Stripe'),
                                       ('PayPal'),
                                       ('NVIDIA'),
                                       ('OpenAI')
    ON DUPLICATE KEY UPDATE
                         company_name = company_name;


INSERT INTO topic (topic_name) VALUES
                                   ('Arrays'),
                                   ('Strings'),
                                   ('Hashing'),
                                   ('Two Pointers'),
                                   ('Sliding Window'),
                                   ('Prefix Sum'),
                                   ('Binary Search'),
                                   ('Sorting'),
                                   ('Linked List'),
                                   ('Stack'),
                                   ('Queue'),
                                   ('Heap'),
                                   ('Trees'),
                                   ('Binary Search Tree'),
                                   ('Trie'),
                                   ('Graphs'),
                                   ('BFS'),
                                   ('DFS'),
                                   ('Backtracking'),
                                   ('Greedy'),
                                   ('Dynamic Programming'),
                                   ('Knapsack'),
                                   ('Bit Manipulation'),
                                   ('Intervals'),
                                   ('Recursion'),
                                   ('Divide and Conquer')
    ON DUPLICATE KEY UPDATE
                         topic_name = topic_name;