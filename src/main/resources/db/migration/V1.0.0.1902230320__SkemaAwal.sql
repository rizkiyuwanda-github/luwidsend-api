
CREATE TABLE public.bank (
                id VARCHAR(5) NOT NULL,
                name VARCHAR(200) NOT NULL,
                CONSTRAINT bank_pk PRIMARY KEY (id)
);


CREATE TABLE public.account (
                id VARCHAR(50) NOT NULL,
                bank_id VARCHAR(5) NOT NULL,
                name VARCHAR(100) NOT NULL,
                balance NUMERIC(19,2) NOT NULL,
                email VARCHAR(100) NOT NULL,
                password VARCHAR(100) NOT NULL,
                access VARCHAR(100) NOT NULL,
                CONSTRAINT account_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN public.account.id IS 'bank id + account number';
COMMENT ON COLUMN public.account.access IS 'CUSTOMER';


CREATE TABLE public.transaction (
                id VARCHAR(50) NOT NULL,
                account_id VARCHAR(50) NOT NULL,
                time TIMESTAMP NOT NULL,
                debit NUMERIC(19,2) NOT NULL,
                credit NUMERIC(19,2) NOT NULL,
                note TEXT,
                CONSTRAINT transaction_pk PRIMARY KEY (id)
);


ALTER TABLE public.account ADD CONSTRAINT bank_account_fk
FOREIGN KEY (bank_id)
REFERENCES public.bank (id)
ON DELETE RESTRICT
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.transaction ADD CONSTRAINT account_transaction_fk
FOREIGN KEY (account_id)
REFERENCES public.account (id)
ON DELETE RESTRICT
ON UPDATE CASCADE
NOT DEFERRABLE;