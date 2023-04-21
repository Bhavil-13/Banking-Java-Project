
create table person(
<<<<<<< HEAD
person_id INT AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
=======
person_id INT not null auto_increment,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
address VARCHAR(1000) NOT NULL,
>>>>>>> 0d03fb5d832224d9a76b756a2e7c436de15b04e0
PRIMARY KEY (person_id)
);
--

create table credit_score(
    salary INT NOT NULL,
    occupation VARCHAR(255),
    net_worth INT NOT NULL,
    score INT NOT NULL,
    person_id INT NOT NULL,
    tloans INT NOT NULL,
    rploans INT NOT NULL
);

create table account(
person_id INT NOT NULL,
<<<<<<< HEAD
account_id INT NOT NULL AUTO_INCREMENT,
=======
account_id INT NOT NULL auto_increment,
>>>>>>> 0d03fb5d832224d9a76b756a2e7c436de15b04e0
balance INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (person_id) REFERENCES person(person_id)
);

create table transactions(
transaction_id INT NOT NULL auto_increment,
sender_acc_id INT NOT NULL,
receiver_acc_id INT NOT NULL,
amount INT NOT NULL,
tran_context CHAR(1) NOT NULL,
loan_id INT ,
PRIMARY KEY (transaction_id),
FOREIGN KEY (sender_acc_id) REFERENCES account(account_id),
FOREIGN KEY (receiver_acc_id) REFERENCES account(account_id)
);

create table loan_applications(
application_id INT NOT NULL auto_increment,
person_id INT NOT NULL,
amount INT NOT NULL,
reason VARCHAR(255) NOT NULL,
account_id INT NOT NULL,
status VARCHAR(255) NOT NULL,
PRIMARY KEY (application_id),
FOREIGN KEY (person_id) REFERENCES person(person_id),
FOREIGN KEY (account_id) REFERENCES account(account_id),
);

create table loans(
loan_id INT NOT NULL auto_increment,
application_id INT NOT NULL,
account_id INT NOT NULL
roi INT NOT NULL,
principal_amt INT NOT NULL,
amt_rem INT NOT NULL,
last_repay INT NOT NULL,
PRIMARY KEY (loan_id),
FOREIGN KEY (application_id) REFERENCES loan_applications(application_id),
FOREIGN KEY (account_id) REFERENCES account(account_id),
);

create table npa(
loan_id INT NOT NULL,
solution VARCHAR(255),
loss INT NOT NULL
);