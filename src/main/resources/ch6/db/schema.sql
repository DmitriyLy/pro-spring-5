CREATE TABLE IF NOT EXISTS singer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    BIRTH_DATE DATE,
    UNIQUE (first_name, last_name)
);

CREATE TABLE IF NOT EXISTS album (
    id SERIAL PRIMARY KEY,
    singer_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    release_date DATE,
    UNIQUE (singer_id, title),
    CONSTRAINT FK_ALBUM FOREIGN KEY (singer_id) REFERENCES singer(id)
);