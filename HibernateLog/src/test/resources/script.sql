CREATE SEQUENCE person_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 15
  CACHE 1;
ALTER TABLE person_id_seq
  OWNER TO postgres;

CREATE TABLE person
(
  first_name character varying(100),
  last_name character varying(100),
  id bigint NOT NULL DEFAULT nextval('person_id_seq'::regclass),
  CONSTRAINT person_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE person
  OWNER TO postgres;
  
CREATE SEQUENCE ltc_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 15
  CACHE 1;
ALTER TABLE ltc_id_seq
  OWNER TO postgres;

CREATE TABLE log_table_column
(
  table_name character varying(100),
  column_name character varying(100),
  old_value character varying(100),
  new_value character varying(100),
  record_id bigint,
  data_mod timestamp without time zone NOT NULL DEFAULT now(),
  id bigint NOT NULL DEFAULT nextval('ltc_id_seq'::regclass),
  CONSTRAINT log_table_column_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE log_table_column
  OWNER TO postgres;  