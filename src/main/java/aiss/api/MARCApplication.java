package aiss.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import aiss.api.resources.AnimesApiResource;
import aiss.api.resources.ImagenesApiResource;
import aiss.api.resources.NoticiasApiResource;
import aiss.api.resources.PerfilesApiResource;
import aiss.api.resources.ProductosApiResource;
import aiss.api.resources.VideosApiResource;

public class MARCApplication extends Application {

	private Set<Object> items = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public MARCApplication() {
		items.add(ProductosApiResource.getInstance());
		items.add(NoticiasApiResource.getInstance());
		items.add(AnimesApiResource.getInstance());
		items.add(ImagenesApiResource.getInstance());
		items.add(VideosApiResource.getInstance());
		items.add(PerfilesApiResource.getInstance());
	}

	public Set<Class<?>> getClasses() {
		return classes;
	}

	public Set<Object> getSingletons() {
		return items;
	}
}
