DROP TABLE IF EXISTS cars;

CREATE TABLE cars
(
    id           VARCHAR(50) PRIMARY KEY,
    registration VARCHAR(250) NOT NULL,
    colour       VARCHAR(250) NOT NULL,
    model        VARCHAR(250) NOT NULL,
    fuelType     VARCHAR(250) NOT NULL
);