CREATE TABLE company (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         company_name VARCHAR(255) NOT NULL,
                         PRIMARY KEY (id),
                         UNIQUE KEY uk_company_name (company_name)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE topic (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       topic_name VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id),
                       UNIQUE KEY uk_topic_name (topic_name)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE problem (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         created DATETIME(6) DEFAULT NULL,
                         difficulty ENUM('EASY','HARD','MEDIUM') NOT NULL,
                         notes VARCHAR(255) DEFAULT NULL,
                         platform_name VARCHAR(255) NOT NULL,
                         problem_link VARCHAR(255) DEFAULT NULL,
                         problem_status ENUM(
        'COULD_NOT_SOLVE',
        'SOLVED_WITHOUT_HELP',
        'SOLVED_WITH_HELP',
        'YET_TO_SOLVE'
    ) DEFAULT NULL,
                         problem_title VARCHAR(255) NOT NULL,
                         updated DATETIME(6) DEFAULT NULL,
                         company_id BIGINT NOT NULL,
                         topic_id BIGINT NOT NULL,

                         PRIMARY KEY (id),

                         KEY idx_problem_company (company_id),
                         KEY idx_problem_topic (topic_id),

                         CONSTRAINT fk_problem_company
                             FOREIGN KEY (company_id)
                                 REFERENCES company (id),

                         CONSTRAINT fk_problem_topic
                             FOREIGN KEY (topic_id)
                                 REFERENCES topic (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;