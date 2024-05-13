CREATE OR REPLACE FUNCTION reinitialize(admin_id BIGINT)
RETURNS VOID AS $$
BEGIN
    DELETE FROM users WHERE users.id != admin_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_quote_details(quote_id bigint)
RETURNS VOID AS $$
BEGIN
    INSERT INTO quote_details(quotes_id, work_details_id, quantity, unit_price) 
    SELECT
        quotes.id,
        house_construction_details.work_details_id,
        house_construction_details.default_quantity,
        work_details.price
    FROM quotes
    JOIN house_construction_details
        ON house_construction_details.house_types_id = quotes.house_types_id
    JOIN work_details
        ON work_details.id = house_construction_details.work_details_id
    WHERE quotes.id = quote_id;
END; $$ LANGUAGE plpgsql;