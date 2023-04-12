

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `account` (`account_id`, `person_id`, `balance`) VALUES
(1, 1, 10000),
(2, 2, 30000),
(3, 3, 50000),
(4, 4, 60000),
(5, 5, 100000),
(6, 6, 505),
(7, 7, 9999999),
(8, 8, 0),
(9, 9, 1);

CREATE TABLE `credit_score` (
  `person_id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `salary` int(11) NOT NULL,
  `net_worth` int(11) NOT NULL,
  `tloans` int(11) NOT NULL,
  `rploans` int(11) NOT NULL,
  `occupation` varchar(255) DEFAULT NULL
)

INSERT INTO `credit_score` (`person_id`, `score`, `salary`, `net_worth`, `tloans`, `rploans`, `occupation`) VALUES
(1, 8, 20000, 1000000, 3, 2, 'Software Engineer'),
(2, 9, 30000, 2000000, 2, 2, 'Bank Maneger'),
(3, 10, 100000, 10000000, 10, 10, 'Software Engineer'),
(4, 7, 50000, 1500000, 5, 2, 'Database Maneger'),
(5, 10, 99000, 10000000, 0, 0, 'Best Teaching Assistant'),
(6, 5, 20000, 100000, 10, 3, 'Chief Minister'),
(7, 10, 2000000, 100000000, 1000, 957, 'Business Tycon'),
(8, 2, 0, 5, 1001, 99, 'Former Businessman'),
(9, 0, 50, 500000, 1012, 0, 'Fugitive');

CREATE TABLE `loans` (
  `loan_id` int(11) NOT NULL,
  `application_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `roi` int(11) NOT NULL,
  `principal` bigint(64) NOT NULL,
  `amt_rem` bigint(64) NOT NULL,
  `last_repay` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `loans` (`loan_id`, `application_id`, `account_id`, `roi`, `principal`, `amt_rem`, `last_repay`) VALUES
(1, 1, 2, 10, 8888, 888, '2023-04-12 22:29:54.000000'),
(2, 4, 9, 1, 100000000000, 100000000000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `loan_applications`
--

CREATE TABLE `loan_applications` (
  `application_id` int(11) NOT NULL,
  `applicant_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loan_applications`
--

INSERT INTO `loan_applications` (`application_id`, `applicant_id`, `account_id`, `amount`, `reason`, `status`) VALUES
(1, 2, 2, 8888, 'Buy Cycle', 'Approved'),
(2, 2, 2, 9999999, 'Pay Collage Fees', 'Pending'),
(3, 6, 6, 777777, 'Run State', 'Pending'),
(4, 9, 9, 2147483647, 'Start new Business', 'Approved');

-- --------------------------------------------------------

--
-- Table structure for table `npa`
--

CREATE TABLE `npa` (
  `loan_id` int(11) NOT NULL,
  `solution` varchar(255) DEFAULT NULL,
  `loss` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(1000) NOT NULL
)

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`person_id`, `name`, `address`) VALUES
('Samarth Sanjay Wankhade', 'Banglore'),
('Samarjeet Sanjay Wankhade', 'Hiwarkhed'),
('Deep Kumar Patel', 'Ahmedabad'),
('Bhavil Anil Sharma', 'Delhi'),
('Jacob Q Matthews', 'Chennai'),
('Arwind K Kejriwal', 'Delhi'),
('Mukesh D Ambani', 'Mumbai'),
('Anil D Ambani', 'Mumbai'),
('Mehul Choksi', 'London');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transaction_id` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  `sender_acc_id` int(11) NOT NULL,
  `receiver_acc_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `tran_context` char(1) NOT NULL,
  `loan_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`),
  ADD KEY `person_id` (`person_id`);

--
-- Indexes for table `loans`
--
ALTER TABLE `loans`
  ADD PRIMARY KEY (`loan_id`),
  ADD KEY `application_id` (`application_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `loan_applications`
--
ALTER TABLE `loan_applications`
  ADD PRIMARY KEY (`application_id`),
  ADD KEY `applicant_id` (`applicant_id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `npa`
--
ALTER TABLE `npa`
  ADD KEY `loan_id` (`loan_id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`person_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `sender_acc_id` (`sender_acc_id`),
  ADD KEY `receiver_acc_id` (`receiver_acc_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`);

--
-- Constraints for table `loans`
--
ALTER TABLE `loans`
  ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `loan_applications` (`application_id`),
  ADD CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Constraints for table `loan_applications`
--
ALTER TABLE `loan_applications`
  ADD CONSTRAINT `loan_applications_ibfk_1` FOREIGN KEY (`applicant_id`) REFERENCES `person` (`person_id`),
  ADD CONSTRAINT `loan_applications_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Constraints for table `npa`
--
ALTER TABLE `npa`
  ADD CONSTRAINT `npa_ibfk_1` FOREIGN KEY (`loan_id`) REFERENCES `loans` (`loan_id`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`sender_acc_id`) REFERENCES `account` (`account_id`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`receiver_acc_id`) REFERENCES `account` (`account_id`);
COMMIT;


