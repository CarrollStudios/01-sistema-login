USE sistema_login;

INSERT INTO usuarios (nome, email, senha)
VALUES ('Usuario Teste', 'teste@exemplo.com', 'senha_teste_sem_uso');

SELECT id, nome, email FROM usuarios;

UPDATE usuarios
SET nome = 'Usuario Teste Atualizado'
WHERE id = 1;

DELETE FROM usuarios
WHERE id = 1;
