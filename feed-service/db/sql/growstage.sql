-- GrowStage static data (Maps to the GrowStageEnum)
delete from growstage;

-- this is static data, no sequence is created and the id must be specified in the insert statement 
insert into growstage (id, name, description) values (0, 'None', 'Not Applicable');
insert into growstage (id, name, description) values (1, 'Germination', 'This is the initial stage where the seed is activated.');
insert into growstage (id, name, description) values (2, 'Seedling', 'The first leaves that appear are usually single-fingered, followed by more complex and multi-fingered leaves.');
insert into growstage (id, name, description) values (3, 'Vegetative', 'This is when the plant builds its overall structure, which will be used to support the buds in the final stage.');
insert into growstage (id, name, description) values (4, 'Flowering', 'This is the final growth stage, which ends when the buds are ripe and ready to be harvested.');
insert into growstage (id, name, description) values (5, 'Harvest', 'The plant is ready to be harvested when most of the pistils have darkened and curled in');
