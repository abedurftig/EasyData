package org.easydata.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EasyClass extends EasyObj {

	public String fileName = null;
	public String targetClassName = null;
	public Set<EasyField> fields = new HashSet<EasyField>();
	@JsonIgnore
	public Set<EasyRelation> relations = new HashSet<EasyRelation>();

	@JsonIgnore
	public Set<EasyRelation> getManyToOneRelations() {
		return getRelationsByType(EasyRelation.RelationType.MANY_TO_ONE);
	}
	
	@JsonIgnore
	public Set<EasyRelation> getOneToManyRelations() {
		return getRelationsByType(EasyRelation.RelationType.ONE_TO_MANY);
	}
	
	@JsonIgnore
	public EasyField getKeyField() {
		
		for (EasyField field : fields) {
			if (field.key) {
				return field;
			}
		}
		return null;
		
	}
	
	@JsonIgnore
	public Set<EasyField> getRefs() {
		
		Set<EasyField> refs = new HashSet<EasyField>(); 
		for (EasyField field : fields) {
			if (field.ref != null && !field.ref.isEmpty()) {
				refs.add(field);
			}
		}
		return refs;
		
	}
	
	public EasyField getFieldByName(String fieldName) {
		
		for (EasyField ef: this.fields) {			
			if (ef.targetFieldName.equals(fieldName)) {
				return ef;
			}
		}
		
		return null;
		
	}
	
	public boolean hasDateField() {
		
		for (EasyField field : fields) {
			if (field.type != null && field.type.equals(EasyType.DATE.getType())) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean hasRefTo(String other) {
		
		for (EasyField field : fields) {
			if (field.ref != null && field.ref.equals(other)) {
				return true;
			}
		}
		return false;
		
	}
	
	private Set<EasyRelation> getRelationsByType(EasyRelation.RelationType type) {
		
		Set<EasyRelation> rels = new HashSet<EasyRelation>();
		for (EasyRelation relation : relations) {
			if (relation.getType().equals(type)) {
				rels.add(relation);
			}
		}
		return rels;
		
	}
	
}
