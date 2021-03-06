DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS ACCOUNT_FILE;

CREATE TABLE ACCOUNT (
      account_id IDENTITY GENERATED BY DEFAULT AS IDENTITY(START WITH 10000) PRIMARY KEY,
      account_name VARCHAR(50) NOT NULL,
      account_type VARCHAR(10) NOT NULL,
      account_status BOOLEAN DEFAULT TRUE,
      add_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      added_by VARCHAR(10) DEFAULT 'SYSTEM',
      updated_ts TIMESTAMP,
      updated_by VARCHAR(10)
);

CREATE TABLE ACCOUNT_FILE (
      file_id INT AUTO_INCREMENT  PRIMARY KEY,
      file_name VARCHAR(100) NOT NULL,
      file BLOB NOT NULL,
      add_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      added_by VARCHAR(10) DEFAULT 'SYSTEM',
      updated_ts TIMESTAMP,
      updated_by VARCHAR(10)
);

INSERT INTO ACCOUNT (account_name, account_type, account_status) VALUES
('Bala', 'DDA', true),
('Vignesh', 'C3', false),
('Vignesh', 'C3', true);