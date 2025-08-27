CREATE TABLE redacao( 
	codigo SERIAL PRIMARY KEY, 
	titulo VARCHAR(50), 
	texto VARCHAR(8192), 
	nota FLOAT
);

INSERT INTO redacao (titulo, texto, nota) VALUES
('A tecnologia como aliada na educação',
 'A inserção da tecnologia na educação transforma a forma de aprender e ensinar, permitindo maior acesso ao conhecimento e inovação nas práticas pedagógicas.',
 9.2),

('Os desafios da saúde pública no Brasil',
 'A precariedade na infraestrutura, somada à má gestão de recursos, compromete a qualidade da saúde pública no país, exigindo políticas efetivas de mudança.',
 8.8),

('A importância da leitura na formação do indivíduo',
 'A leitura estimula o senso crítico, amplia o vocabulário e forma cidadãos mais conscientes e preparados para os desafios sociais.',
 9.2),

('Meio ambiente e responsabilidade coletiva',
 'A preservação ambiental depende da ação conjunta entre governo, empresas e sociedade civil, buscando reduzir impactos e garantir sustentabilidade.',
 7.8),

('A violência urbana e seus impactos sociais',
 'O aumento da violência nas cidades brasileiras compromete a qualidade de vida e a sensação de segurança, exigindo políticas públicas eficazes.',
 7.5),

('Educação inclusiva: um desafio contemporâneo',
 'Garantir a inclusão de pessoas com deficiência na escola é fundamental para a construção de uma sociedade justa e democrática.',
 8.8),

('O papel das redes sociais na democracia',
 'As redes sociais ampliaram a circulação de ideias, mas também trouxeram riscos como a disseminação de fake news, impactando a formação da opinião pública.',
 8.0),

('A valorização da cultura brasileira',
 'A cultura nacional é diversa e rica, porém muitas vezes negligenciada; sua valorização fortalece a identidade e o pertencimento social.',
 7.5),

('Os impactos do consumismo na sociedade',
 'O consumismo desenfreado gera consequências econômicas, sociais e ambientais, reforçando a necessidade de repensar hábitos e estimular o consumo consciente.',
 7.8),

('Educação financeira para jovens',
 'O ensino da educação financeira nas escolas possibilita maior autonomia, planejamento e preparo para lidar com os desafios da vida adulta.',
 8.8);