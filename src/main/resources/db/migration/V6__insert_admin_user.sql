INSERT INTO users(
    username,
    password,
    role,
    created_at,
    updated_at
)
VALUES
    (
        'admin',
        '$2a$10$7EqJtq98hPqEX7fNZaFWoOHiJ4JzW9f1rN4P8QnD9A3WwN5wK6hYy',
        'ADMIN',
        NOW(),
        NOW()
    );