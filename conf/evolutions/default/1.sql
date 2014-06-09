# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table competencia (
  id                        bigint not null,
  nome                      varchar(255) not null,
  constraint pk_competencia primary key (id))
;

create table disciplina (
  id                        bigint not null,
  nome                      varchar(255) not null,
  sigla                     varchar(255) not null,
  periodo_id                bigint,
  constraint pk_disciplina primary key (id))
;

create table habilidade (
  id                        bigint not null,
  descricao                 varchar(255),
  nivel                     integer,
  competencia_id            bigint,
  constraint ck_habilidade_nivel check (nivel in (0,1,2)),
  constraint pk_habilidade primary key (id))
;

create table periodo (
  id                        bigint not null,
  nome                      varchar(255) not null,
  constraint pk_periodo primary key (id))
;

create table relacao (
  id                        bigint not null,
  verbo                     varchar(255) not null,
  tipo_relacao              integer,
  habilidade_id             bigint,
  disciplina_id             bigint,
  constraint ck_relacao_tipo_relacao check (tipo_relacao in (0,1)),
  constraint pk_relacao primary key (id))
;

create sequence competencia_seq;

create sequence disciplina_seq;

create sequence habilidade_seq;

create sequence periodo_seq;

create sequence relacao_seq;

alter table disciplina add constraint fk_disciplina_periodo_1 foreign key (periodo_id) references periodo (id) on delete restrict on update restrict;
create index ix_disciplina_periodo_1 on disciplina (periodo_id);
alter table habilidade add constraint fk_habilidade_competencia_2 foreign key (competencia_id) references competencia (id) on delete restrict on update restrict;
create index ix_habilidade_competencia_2 on habilidade (competencia_id);
alter table relacao add constraint fk_relacao_habilidade_3 foreign key (habilidade_id) references habilidade (id) on delete restrict on update restrict;
create index ix_relacao_habilidade_3 on relacao (habilidade_id);
alter table relacao add constraint fk_relacao_disciplina_4 foreign key (disciplina_id) references disciplina (id) on delete restrict on update restrict;
create index ix_relacao_disciplina_4 on relacao (disciplina_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists competencia;

drop table if exists disciplina;

drop table if exists habilidade;

drop table if exists periodo;

drop table if exists relacao;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists competencia_seq;

drop sequence if exists disciplina_seq;

drop sequence if exists habilidade_seq;

drop sequence if exists periodo_seq;

drop sequence if exists relacao_seq;

