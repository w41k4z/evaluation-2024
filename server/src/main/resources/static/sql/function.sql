CREATE OR REPLACE FUNCTION reinitialize(admin_id BIGINT)
RETURNS VOID AS $$
BEGIN
    DELETE FROM users WHERE users.id != admin_id;
END;
$$ LANGUAGE plpgsql;
