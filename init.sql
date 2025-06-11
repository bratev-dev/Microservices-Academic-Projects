-- Crear tabla 'companies'
CREATE TABLE IF NOT EXISTS companies (
    NIT VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    sector VARCHAR(255),
    contact_names VARCHAR(255),
    contact_last_names VARCHAR(255),
    contact_phone_number VARCHAR(50),
    contact_position VARCHAR(100),
    password VARCHAR(255)
);

-- Insertar empresas de ejemplo
INSERT INTO companies (NIT, name, email, sector, contact_names, contact_last_names, contact_phone_number, contact_position, password)
VALUES
('123456789', 'Tech Innovadores S.A.', 'contacto@techinnovadores.com', 'Tecnología', 'Carlos', 'Pérez', '3216549870', 'Gerente de Proyectos', 'clave123'),
('987654321', 'Soluciones Verdes Ltda.', 'info@solucionesverdes.com', 'Medio Ambiente', 'Lucía', 'Martínez', '3129874561', 'Coordinadora General', 'verde456');

-- Crear tabla 'projects'
CREATE TABLE IF NOT EXISTS projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    summary TEXT,
    goals TEXT,
    description TEXT,
    maxtime_months INTEGER,
    budget DOUBLE PRECISION,
    date DATE,
    status VARCHAR(50),
    comments TEXT,
    company_id VARCHAR(255),
    assigned_to BIGINT,
    CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES companies(NIT) ON DELETE SET NULL
);

-- Insertar proyectos de ejemplo
INSERT INTO projects (name, summary, goals, description, maxtime_months, budget, date, status, comments, company_id, assigned_to)
VALUES
('Sistema de Evaluación', 'Evaluar a estudiantes en prácticas', 'Generar reportes', 'Aplicación web completa', 6, 15000.0, '2025-06-01', 'RECEIVED', 'Revisar requerimientos', '123456789', NULL),
('Chat Interno', 'Comunicación rápida entre usuarios', 'Interacción en tiempo real', 'App de mensajería', 4, 8000.0, '2025-05-15', 'IN_PROGRESS', 'Integrando WebSocket', '987654321', 3);
