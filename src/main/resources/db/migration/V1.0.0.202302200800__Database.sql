
CREATE TABLE public.user_app (
                id VARCHAR(100) NOT NULL,
                password VARCHAR(100) NOT NULL,
                role VARCHAR(50) NOT NULL,
                account_id VARCHAR(50),
                CONSTRAINT user_app_pk PRIMARY KEY (id)
);


CREATE TABLE public.transaction (
                id VARCHAR(50) NOT NULL,
                sender_account_id VARCHAR(50) NOT NULL,
                sender_bank_id VARCHAR(5) NOT NULL,
                sender_name VARCHAR(100) NOT NULL,
                receiver_account_id VARCHAR(50) NOT NULL,
                receiver_bank_id VARCHAR(5) NOT NULL,
                receiver_name VARCHAR(100) NOT NULL,
                time TIMESTAMP NOT NULL,
                amount NUMERIC(19,2) NOT NULL,
                note TEXT,
                CONSTRAINT transaction_pk PRIMARY KEY (id)
);


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
                CONSTRAINT account_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN public.account.id IS 'bank id + account number';


ALTER TABLE public.account ADD CONSTRAINT bank_account_fk
FOREIGN KEY (bank_id)
REFERENCES public.bank (id)
ON DELETE RESTRICT
ON UPDATE CASCADE
NOT DEFERRABLE;