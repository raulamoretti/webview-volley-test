package br.com.twounknow.bairesapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListRepo {

@SerializedName("total_count")
@Expose
public Integer totalCount;
@SerializedName("incomplete_results")
@Expose
public Boolean incompleteResults;
@SerializedName("items")
@Expose
public List<Item> items = null;

}