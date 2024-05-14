CREATE VIEW v_house_types AS
SELECT
    house_types.id,
    house_types.name,
    house_types.duration,
    SUM(house_construction_details.default_quantity * work_details.price) AS total_price
FROM house_types
JOIN house_construction_details
    ON house_construction_details.house_types_id = house_types.id
JOIN work_details
    ON work_details.id = house_construction_details.work_details_id
GROUP BY
    house_types.id
;

CREATE VIEW v_quote_details AS
SELECT
    row_number() OVER(ORDER BY quotes.id) AS row_number,
    quotes.id,
    quotes.action_date,
    quotes.users_id,
    work_details.works_id,
    works.name AS works_name,
    quote_details.work_details_id,
    work_details.designation AS work_details_designation,
    work_details.units_id,
    units.name AS units_name,
    quote_details.quantity,
    quote_details.unit_price
FROM quotes
JOIN quote_details
    ON quote_details.quotes_id = quotes.id
JOIN work_details
    ON work_details.id = quote_details.work_details_id
JOIN works
    ON works.id = work_details.works_id
JOIN units
    ON units.id = work_details.units_id
;

SELECT
FROM payment

CREATE VIEW v_quotes_total_payments AS
SELECT
    quotes.id,
    quotes.action_date,
    users.username,
    house_types.name AS house_types_name,
    finition_types.name AS finition_types_name,
    quotes.finition_type_majoration,
    quotes.construction_start_date,
    quotes.construction_end_date,
    quotes.total_price,
    COALESCE(SUM(payment.amount), 0) AS total_payments
FROM quotes
JOIN users
    ON users.id = quotes.users_id
JOIN house_types
    ON house_types.id = quotes.house_types_id
JOIN finition_types
    ON finition_types.id = quotes.finition_types_id
LEFT JOIN payment
    ON payment.quotes_id = quotes.id
    AND payment.action_date BETWEEN quotes.action_date AND quotes.construction_end_date
GROUP BY 
    quotes.id,
    users.username,
    house_types.name,
    finition_types.name
;

CREATE VIEW v_current_quotes AS
SELECT
    v_quotes_total_payments.*
FROM v_quotes_total_payments
WHERE NOW() BETWEEN v_quotes_total_payments.action_date AND v_quotes_total_payments.construction_end_date
;

CREATE VIEW v_global_quote_total_amount AS
SELECT
    SUM(total_price) AS total_amount,
    SUM(total_payments) AS total_payments
FROM v_quotes_total_payments;

CREATE VIEW v_current_quote_total_amount AS
SELECT
    SUM(total_price) AS total_amount,
    SUM(total_payments) AS total_payments
FROM v_current_quotes;

CREATE VIEW v_months AS
SELECT * FROM generate_series(1, 12);

CREATE VIEW v_quote_total_amount_per_month AS
SELECT
    EXTRACT(MONTH FROM action_date) AS month,
    EXTRACT(YEAR FROM action_date) AS year,
    SUM(total_price) AS total_amount,
    SUM(total_payments) AS total_payments
FROM v_quotes_total_payments
GROUP BY
    month,
    year
;