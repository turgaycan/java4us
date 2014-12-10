--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4beta2
-- Dumped by pg_dump version 9.4beta2
-- Started on 2014-10-03 22:32:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE "JAVA4US";
--
-- TOC entry 2088 (class 1262 OID 16394)
-- Name: JAVA4US; Type: DATABASE; Schema: -; Owner: java4us
--

CREATE DATABASE "JAVA4US" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Turkish_Turkey.1254' LC_CTYPE = 'Turkish_Turkey.1254';


ALTER DATABASE "JAVA4US" OWNER TO java4us;

/**connect "JAVA4US" **/

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 16395)
-- Name: java4us; Type: SCHEMA; Schema: -; Owner: java4us
--

CREATE SCHEMA java4us;


ALTER SCHEMA java4us OWNER TO java4us;

--
-- TOC entry 187 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 187
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 186 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 186
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = java4us, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 16398)
-- Name: configuration; Type: TABLE; Schema: java4us; Owner: java4us; Tablespace: 
--

CREATE TABLE configuration (
    id bigint NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    category character varying(250) NOT NULL,
    key character varying(250) NOT NULL,
    value character varying(250) NOT NULL
);


ALTER TABLE configuration OWNER TO java4us;

--
-- TOC entry 172 (class 1259 OID 16396)
-- Name: configuration_id_seq; Type: SEQUENCE; Schema: java4us; Owner: java4us
--

CREATE SEQUENCE configuration_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE configuration_id_seq OWNER TO java4us;

--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 172
-- Name: configuration_id_seq; Type: SEQUENCE OWNED BY; Schema: java4us; Owner: java4us
--

ALTER SEQUENCE configuration_id_seq OWNED BY configuration.id;


--
-- TOC entry 179 (class 1259 OID 16456)
-- Name: feed; Type: TABLE; Schema: java4us; Owner: java4us; Tablespace: 
--

CREATE TABLE feed (
    id bigint NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    link character varying(500) NOT NULL,
    lang character varying(100),
    copyright boolean DEFAULT false,
    feeder_id bigint NOT NULL,
    category character varying(100),
    pubdate timestamp without time zone DEFAULT now(),
    createdate timestamp without time zone DEFAULT now(),
    status character varying(100) NOT NULL
);


ALTER TABLE feed OWNER TO java4us;

--
-- TOC entry 178 (class 1259 OID 16454)
-- Name: feed_id_seq; Type: SEQUENCE; Schema: java4us; Owner: java4us
--

CREATE SEQUENCE feed_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE feed_id_seq OWNER TO java4us;

--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 178
-- Name: feed_id_seq; Type: SEQUENCE OWNED BY; Schema: java4us; Owner: java4us
--

ALTER SEQUENCE feed_id_seq OWNED BY feed.id;


--
-- TOC entry 177 (class 1259 OID 16433)
-- Name: feeder; Type: TABLE; Schema: java4us; Owner: java4us; Tablespace: 
--

CREATE TABLE feeder (
    id bigint NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    name character varying(100) ,
    surname character varying(100),
    email character varying(100) NOT NULL,
    domain character varying(100) NOT NULL,
    status character varying(100) NOT NULL,
    createdate timestamp without time zone DEFAULT now()
);


ALTER TABLE feeder OWNER TO java4us;

--
-- TOC entry 176 (class 1259 OID 16431)
-- Name: feeder_id_seq; Type: SEQUENCE; Schema: java4us; Owner: java4us
--

CREATE SEQUENCE feeder_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE feeder_id_seq OWNER TO java4us;

--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 176
-- Name: feeder_id_seq; Type: SEQUENCE OWNED BY; Schema: java4us; Owner: java4us
--

ALTER SEQUENCE feeder_id_seq OWNED BY feeder.id;


--
-- TOC entry 181 (class 1259 OID 16484)
-- Name: feedmessage; Type: TABLE; Schema: java4us; Owner: java4us; Tablespace: 
--

CREATE TABLE feedmessage (
    id bigint NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    title character varying(500) NOT NULL,
    description character varying(4000),
    link character varying(500) NOT NULL,
    author character varying(100),
    guid character varying(100),
    feed_id bigint NOT NULL,
    category character varying(100),
    pubdate timestamp without time zone DEFAULT now(),
    createdate timestamp without time zone DEFAULT now(),
    status character varying(100) NOT NULL,
    proceed boolean DEFAULT false NOT NULL
);


ALTER TABLE feedmessage OWNER TO java4us;

--
-- TOC entry 180 (class 1259 OID 16482)
-- Name: feedmessage_id_seq; Type: SEQUENCE; Schema: java4us; Owner: java4us
--

CREATE SEQUENCE feedmessage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE feedmessage_id_seq OWNER TO java4us;

--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 180
-- Name: feedmessage_id_seq; Type: SEQUENCE OWNED BY; Schema: java4us; Owner: java4us
--

ALTER SEQUENCE feedmessage_id_seq OWNED BY feedmessage.id;


--
-- TOC entry 175 (class 1259 OID 16421)
-- Name: subscriber; Type: TABLE; Schema: java4us; Owner: java4us; Tablespace: 
--

CREATE TABLE subscriber (
    id bigint NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    name character varying(100) NOT NULL,
    surname character varying(100) NOT NULL,
    email character varying(250) NOT NULL,
    createdate timestamp without time zone DEFAULT now()
);


ALTER TABLE subscriber OWNER TO java4us;

--
-- TOC entry 174 (class 1259 OID 16419)
-- Name: subscriber_id_seq; Type: SEQUENCE; Schema: java4us; Owner: java4us
--

CREATE SEQUENCE subscriber_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE subscriber_id_seq OWNER TO java4us;

--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 174
-- Name: subscriber_id_seq; Type: SEQUENCE OWNED BY; Schema: java4us; Owner: java4us
--

ALTER SEQUENCE subscriber_id_seq OWNED BY subscriber.id;


--
-- TOC entry 183 (class 1259 OID 16507)
-- Name: user; Type: TABLE; Schema: java4us; Owner: java4us; Tablespace: 
--

CREATE TABLE "user" (
    id bigint NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    login character varying(50) NOT NULL,
    createdate timestamp without time zone DEFAULT now(),
    status character varying(100) NOT NULL
);


ALTER TABLE "user" OWNER TO java4us;

--
-- TOC entry 182 (class 1259 OID 16505)
-- Name: user_id_seq; Type: SEQUENCE; Schema: java4us; Owner: java4us
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO java4us;

--
-- TOC entry 2103 (class 0 OID 0)
-- Dependencies: 182
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: java4us; Owner: java4us
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- TOC entry 185 (class 1259 OID 16526)
-- Name: user_roles; Type: TABLE; Schema: java4us; Owner: java4us; Tablespace: 
--

CREATE TABLE user_roles (
    id bigint NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    role character varying(100) NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE user_roles OWNER TO java4us;

--
-- TOC entry 184 (class 1259 OID 16524)
-- Name: user_roles_id_seq; Type: SEQUENCE; Schema: java4us; Owner: java4us
--

CREATE SEQUENCE user_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_roles_id_seq OWNER TO java4us;

--
-- TOC entry 2105 (class 0 OID 0)
-- Dependencies: 184
-- Name: user_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: java4us; Owner: java4us
--

ALTER SEQUENCE user_roles_id_seq OWNED BY user_roles.id;


--
-- TOC entry 1922 (class 2604 OID 16401)
-- Name: id; Type: DEFAULT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY configuration ALTER COLUMN id SET DEFAULT nextval('configuration_id_seq'::regclass);


--
-- TOC entry 1930 (class 2604 OID 16459)
-- Name: id; Type: DEFAULT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY feed ALTER COLUMN id SET DEFAULT nextval('feed_id_seq'::regclass);


--
-- TOC entry 1927 (class 2604 OID 16436)
-- Name: id; Type: DEFAULT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY feeder ALTER COLUMN id SET DEFAULT nextval('feeder_id_seq'::regclass);


--
-- TOC entry 1935 (class 2604 OID 16487)
-- Name: id; Type: DEFAULT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY feedmessage ALTER COLUMN id SET DEFAULT nextval('feedmessage_id_seq'::regclass);


--
-- TOC entry 1924 (class 2604 OID 16424)
-- Name: id; Type: DEFAULT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY subscriber ALTER COLUMN id SET DEFAULT nextval('subscriber_id_seq'::regclass);


--
-- TOC entry 1940 (class 2604 OID 16510)
-- Name: id; Type: DEFAULT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 1943 (class 2604 OID 16529)
-- Name: id; Type: DEFAULT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY user_roles ALTER COLUMN id SET DEFAULT nextval('user_roles_id_seq'::regclass);


--
-- TOC entry 1946 (class 2606 OID 16407)
-- Name: pk_configuration_id; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY configuration
    ADD CONSTRAINT pk_configuration_id PRIMARY KEY (id);


--
-- TOC entry 1959 (class 2606 OID 16468)
-- Name: pk_feed_id; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY feed
    ADD CONSTRAINT pk_feed_id PRIMARY KEY (id);


--
-- TOC entry 1954 (class 2606 OID 16443)
-- Name: pk_feeder_id; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY feeder
    ADD CONSTRAINT pk_feeder_id PRIMARY KEY (id);


--
-- TOC entry 1963 (class 2606 OID 16496)
-- Name: pk_feedmessage_id; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY feedmessage
    ADD CONSTRAINT pk_feedmessage_id PRIMARY KEY (id);


--
-- TOC entry 1950 (class 2606 OID 16428)
-- Name: pk_subscriber_id; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY subscriber
    ADD CONSTRAINT pk_subscriber_id PRIMARY KEY (id);


--
-- TOC entry 1967 (class 2606 OID 16514)
-- Name: pk_user_id; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT pk_user_id PRIMARY KEY (id);


--
-- TOC entry 1971 (class 2606 OID 16532)
-- Name: pk_user_roles_id; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT pk_user_roles_id PRIMARY KEY (id);


--
-- TOC entry 1948 (class 2606 OID 16409)
-- Name: unique_configuration_key; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY configuration
    ADD CONSTRAINT unique_configuration_key UNIQUE (key);


--
-- TOC entry 1961 (class 2606 OID 16470)
-- Name: unique_feed_link; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY feed
    ADD CONSTRAINT unique_feed_link UNIQUE (link);


--
-- TOC entry 1956 (class 2606 OID 16445)
-- Name: unique_feeder_domain; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY feeder
    ADD CONSTRAINT unique_feeder_domain UNIQUE (domain);


--
-- TOC entry 1965 (class 2606 OID 16498)
-- Name: unique_feedmessage_link; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY feedmessage
    ADD CONSTRAINT unique_feedmessage_link UNIQUE (link);


--
-- TOC entry 1952 (class 2606 OID 16430)
-- Name: unique_subscriber_email; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY subscriber
    ADD CONSTRAINT unique_subscriber_email UNIQUE (email);


--
-- TOC entry 1969 (class 2606 OID 16516)
-- Name: unique_user_email; Type: CONSTRAINT; Schema: java4us; Owner: java4us; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT unique_user_email UNIQUE (email);


--
-- TOC entry 1957 (class 1259 OID 16481)
-- Name: fki_feed_feeder; Type: INDEX; Schema: java4us; Owner: java4us; Tablespace: 
--

CREATE INDEX fki_feed_feeder ON feed USING btree (feeder_id);


--
-- TOC entry 1972 (class 2606 OID 16476)
-- Name: fk_feed_feeder; Type: FK CONSTRAINT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY feed
    ADD CONSTRAINT fk_feed_feeder FOREIGN KEY (feeder_id) REFERENCES feeder(id);


--
-- TOC entry 1973 (class 2606 OID 16499)
-- Name: fk_feedmessage_feed; Type: FK CONSTRAINT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY feedmessage
    ADD CONSTRAINT fk_feedmessage_feed FOREIGN KEY (feed_id) REFERENCES feed(id);


--
-- TOC entry 1974 (class 2606 OID 16533)
-- Name: fk_user_roles_user; Type: FK CONSTRAINT; Schema: java4us; Owner: java4us
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 6
-- Name: java4us; Type: ACL; Schema: -; Owner: java4us
--

REVOKE ALL ON SCHEMA java4us FROM PUBLIC;
REVOKE ALL ON SCHEMA java4us FROM java4us;
GRANT ALL ON SCHEMA java4us TO java4us;
GRANT ALL ON SCHEMA java4us TO PUBLIC;


--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 173
-- Name: configuration; Type: ACL; Schema: java4us; Owner: java4us
--

REVOKE ALL ON TABLE configuration FROM PUBLIC;
REVOKE ALL ON TABLE configuration FROM java4us;
GRANT ALL ON TABLE configuration TO java4us;
GRANT ALL ON TABLE configuration TO PUBLIC;


--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 179
-- Name: feed; Type: ACL; Schema: java4us; Owner: java4us
--

REVOKE ALL ON TABLE feed FROM PUBLIC;
REVOKE ALL ON TABLE feed FROM java4us;
GRANT ALL ON TABLE feed TO java4us;


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 177
-- Name: feeder; Type: ACL; Schema: java4us; Owner: java4us
--

REVOKE ALL ON TABLE feeder FROM PUBLIC;
REVOKE ALL ON TABLE feeder FROM java4us;
GRANT ALL ON TABLE feeder TO java4us;


--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 181
-- Name: feedmessage; Type: ACL; Schema: java4us; Owner: java4us
--

REVOKE ALL ON TABLE feedmessage FROM PUBLIC;
REVOKE ALL ON TABLE feedmessage FROM java4us;
GRANT ALL ON TABLE feedmessage TO java4us;


--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 175
-- Name: subscriber; Type: ACL; Schema: java4us; Owner: java4us
--

REVOKE ALL ON TABLE subscriber FROM PUBLIC;
REVOKE ALL ON TABLE subscriber FROM java4us;
GRANT ALL ON TABLE subscriber TO java4us;


--
-- TOC entry 2102 (class 0 OID 0)
-- Dependencies: 183
-- Name: user; Type: ACL; Schema: java4us; Owner: java4us
--

REVOKE ALL ON TABLE "user" FROM PUBLIC;
REVOKE ALL ON TABLE "user" FROM java4us;
GRANT ALL ON TABLE "user" TO java4us;


--
-- TOC entry 2104 (class 0 OID 0)
-- Dependencies: 185
-- Name: user_roles; Type: ACL; Schema: java4us; Owner: java4us
--

REVOKE ALL ON TABLE user_roles FROM PUBLIC;
REVOKE ALL ON TABLE user_roles FROM java4us;
GRANT ALL ON TABLE user_roles TO java4us;


-- Completed on 2014-10-03 22:32:09

--
-- Create message_resources
--
CREATE TABLE java4us.message_resource
(
  id bigserial NOT NULL,
  deleted boolean NOT NULL DEFAULT false,
  category character varying(100) NOT NULL,  
  key character varying(100) NOT NULL,
  trvalue character varying(100) NOT NULL,
  envalue character varying(100) NOT NULL,
  CONSTRAINT pk_message_resource_id PRIMARY KEY (id),
    CONSTRAINT unique_message_resource_key UNIQUE (key)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE java4us.message_resource
  OWNER TO java4us;
GRANT ALL ON TABLE java4us.message_resource TO java4us;


-- Create AUD_FEEDER SQL Script 
--

CREATE TABLE java4us.aud_feeder
(
  id bigint,
  rev bigint,
  revtype character varying(3),
  deleted boolean,
  name character varying(100),
  surname character varying(100),
  email character varying(100) ,
  domain character varying(100),
  status character varying(100) ,
  createdate timestamp without time zone
)
WITH (
  OIDS=FALSE
);
ALTER TABLE java4us.feeder
  OWNER TO java4us;
GRANT ALL ON TABLE java4us.feeder TO java4us;

--
-- Create Contact Table
--
CREATE TABLE java4us.contact (
  id bigint NOT NULL,
  deleted boolean DEFAULT false NOT NULL,
  email character varying(100) NOT NULL,
  content character varying(4000) NOT NULL,
  createdate timestamp without time zone DEFAULT now()
);

ALTER TABLE java4us.contact ADD CONSTRAINT pk_contact_id PRIMARY KEY (id);

ALTER TABLE java4us.contact
OWNER TO java4us;
GRANT ALL ON TABLE java4us.contact TO java4us;

--
-- TOC entry 174 (class 1259 OID 16419)
-- Name: contact_id_seq; Type: SEQUENCE; Schema: java4us; Owner: java4us
--

CREATE SEQUENCE java4us.contact_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE java4us.contact_id_seq OWNER TO java4us;

--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 174
-- Name: contact_id_seq; Type: SEQUENCE OWNED BY; Schema: java4us; Owner: java4us
--

ALTER SEQUENCE java4us.contact_id_seq OWNED BY java4us.contact.id;

--
-- SEQ TRIGGER
--
ALTER TABLE ONLY java4us.contact ALTER COLUMN id SET DEFAULT nextval('java4us.contact_id_seq'::regclass);

--
-- ADD COLUMN CONTACT
--

ALTER TABLE java4us.contact  ADD COLUMN type character varying(50) NOT NULL;

--
-- ADD COLUMNS FEEDMESSAGE
--

ALTER TABLE java4us.feedmessage  ADD COLUMN viewcount integer default 0;
ALTER TABLE java4us.feedmessage  ADD COLUMN gotolinkcount integer default 0;




