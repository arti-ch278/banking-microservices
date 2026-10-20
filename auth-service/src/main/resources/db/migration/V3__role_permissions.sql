CREATE TABLE permissions(
	id BUGSERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE NOT NULL	
);
CREATE TABLE role_permissions(
	role_id BIGINT NOT NULL,
	permission_id BIGINT NOT NULL,
	PRIMARY KEY(role_id, permission_id), 
	CONSTRAINT fk_role_permission_role FOREIGN KEY(role_id) REFERENCES roles(id),
	CONSTRAINT fk_role_permission_permission FOREIGN KEY(permission_id) REFERENCES permission(id),
	);
	
INSERT INTO roles(name) VALUE
('ROLE_ADMIN'),
('ROLE_BANKER'),
('ROLE_CUSTOMER');
INSERT INTO permissions(name) VALUE
('CREATE_ACCOUNT'),
('VIEW_ACCOUNT'),
('TRANSFER_MONEY'),
('APPROVE_TRANSACTION');

