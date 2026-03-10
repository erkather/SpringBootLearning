CREATE SEQUENCE user_info_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE user_info (
    user_id BIGINT PRIMARY KEY DEFAULT nextval('user_info_sequence'),
    name VARCHAR(100) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    dob date NOT NULL,
    lost_login TIMESTAMPTZ,

    CONSTRAINT uk_user_email UNIQUE (email)
);

CREATE TABLE bus_operator (
    operator_id BIGINT PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    contact_number VARCHAR(20) NOT NULL,
    address VARCHAR(500) NOT NULL,

    CONSTRAINT uk_bus_operator_company_name UNIQUE (company_name),
    CONSTRAINT uk_bus_operator_contact_number UNIQUE (contact_number),
    CONSTRAINT fk_operator_user_id FOREIGN KEY (operator_id) REFERENCES user_info(user_id) ON DELETE CASCADE
);

CREATE SEQUENCE bus_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE bus (
	id BIGINT PRIMARY KEY DEFAULT nextval('bus_sequence'),
	bus_number VARCHAR(25) NOT NULL,
	type VARCHAR(100) NOT NULL,
	manufacturer VARCHAR(100) NOT NULL,
	operator_id BIGINT NOT NULL,

    CONSTRAINT fk_bus_operator_id FOREIGN KEY (operator_id) REFERENCES bus_operator(operator_id) ON DELETE CASCADE,
	CONSTRAINT uk_bus_bus_number UNIQUE (bus_number));

CREATE SEQUENCE seat_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE seat (
	id BIGINT PRIMARY KEY DEFAULT nextval('seat_sequence'),
	seat_number VARCHAR(10) NOT NULL,
	type VARCHAR(25) NOT NULL,
	bus_id bigint,

	CONSTRAINT fk_seat_bus_id FOREIGN KEY (bus_id) REFERENCES bus(id) ON DELETE CASCADE);

CREATE INDEX idx_seat_bus_id ON seat(bus_id);

CREATE SEQUENCE bus_stop_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE bus_stop (
    id BIGINT PRIMARY KEY DEFAULT nextval('bus_stop_sequence'),
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE SEQUENCE bus_route_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE bus_route (
    id BIGINT PRIMARY KEY DEFAULT nextval('bus_route_sequence'),
    bus_id BIGINT,
    start_date TIMESTAMPTZ NOT NULL,
    end_date TIMESTAMPTZ NOT NULL,

    CONSTRAINT fk_bus_route_bus_id FOREIGN KEY (bus_id) REFERENCES bus(id) ON DELETE CASCADE
);

CREATE INDEX idx_bus_route_bus_id ON bus_route(bus_id);

CREATE SEQUENCE bus_route_boarding_point_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE bus_route_boarding_point (
    id BIGINT PRIMARY KEY DEFAULT nextval('bus_route_boarding_point_sequence'),
    bus_route_id BIGINT,
    bus_stop_id BIGINT,
    stop_order INT,
    boarding_time TIME NOT NULL,
    CONSTRAINT uk_bus_route_boarding_point UNIQUE (bus_route_id, bus_stop_id),
    CONSTRAINT fk_boarding_point_bus_route_id FOREIGN KEY (bus_route_id) REFERENCES bus_route(id) ON DELETE CASCADE,
    CONSTRAINT fk_boarding_point_bus_stop_id FOREIGN KEY (bus_stop_id) REFERENCES bus_stop(id) ON DELETE CASCADE
);

CREATE SEQUENCE bus_route_dropping_point_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE bus_route_dropping_point (
    id BIGINT PRIMARY KEY DEFAULT nextval('bus_route_dropping_point_sequence'),
    bus_route_id BIGINT,
    bus_stop_id BIGINT,
    stop_order INT,
    dropping_time TIME NOT NULL,
    CONSTRAINT uk_bus_route_dropping_point UNIQUE (bus_route_id, bus_stop_id),
    CONSTRAINT fk_dropping_point_bus_route_id FOREIGN KEY (bus_route_id) REFERENCES bus_route(id) ON DELETE CASCADE,
    CONSTRAINT fk_dropping_point_bus_stop_id FOREIGN KEY (bus_stop_id) REFERENCES bus_stop(id) ON DELETE CASCADE
);

CREATE TABLE bus_route_service_days (
    id BIGINT PRIMARY KEY,
    sunday BOOLEAN DEFAULT TRUE,
    monday BOOLEAN DEFAULT TRUE,
    tuesday BOOLEAN DEFAULT TRUE,
    wednesday BOOLEAN DEFAULT TRUE,
    thursday BOOLEAN DEFAULT TRUE,
    friday BOOLEAN DEFAULT TRUE,
    saturday BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_service_days_bus_route_id FOREIGN KEY (id) REFERENCES bus_route(id) ON DELETE CASCADE
);