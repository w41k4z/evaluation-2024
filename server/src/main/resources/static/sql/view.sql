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