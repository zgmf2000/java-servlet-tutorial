package servletPackage;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SimpleCrudBean implements Serializable {
private static final long serialVersionUID = 1L;
    
    private List<Book> list;
    private Book item = new Book();
    private Book beforeEditItem = null;
    private boolean edit;
    private boolean emptyIsbn = false;
	private boolean emptyTitle = false;
    private boolean emptyPrice = false;
    
    public boolean isEmptyIsbn() {
		return emptyIsbn;
	}

	public void setEmptyIsbn(boolean emptyIsbn) {
		this.emptyIsbn = emptyIsbn;
	}

	public boolean isEmptyTitle() {
		return emptyTitle;
	}

	public void setEmptyTitle(boolean emptyTitle) {
		this.emptyTitle = emptyTitle;
	}

	public boolean isEmptyPrice() {
		return emptyPrice;
	}

	public void setEmptyPrice(boolean emptyPrice) {
		this.emptyPrice = emptyPrice;
	}
	
	public void resetLabels()
	{
		setEmptyIsbn(false);
		setEmptyTitle(false);
		setEmptyPrice(false);
	}
	
	public boolean validateForm(Book item)
	{
		boolean flag = true;
        if (item.getIsbn().isEmpty())
        {
        	setEmptyIsbn(true);
        	flag = false;
        }
        
        if (item.getTitle().isEmpty())
        {
        	setEmptyTitle(true);
        	flag = false;
        }
        
        if (item.getPrice() == 0)
        {
        	setEmptyPrice(true);
        	flag = false;
        }
        
        return flag;
	}
    
    @PostConstruct
    public void init() {
        list = new ArrayList<Book>();
    }
    
    public void add() {
        // DAO save the add
        item.setId(list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1);
        resetLabels();
        if (validateForm(item))
        {
        	list.add(item);
            item = new Book();
        }
    }
    
    public void resetAdd() {
        item = new Book();
    }
    
    public void edit(Book item) {
        beforeEditItem = item.clone();
        this.item = item;
        edit = true;
    }
    
    public void cancelEdit() {
        this.item.restore(beforeEditItem);
        this.item = new Book();
        edit = false;
    }
    
    public void saveEdit() {
        // DAO save the edit
    	resetLabels();
    	if (!validateForm(item))
    	{
    		this.item.restore(beforeEditItem);
    		return;
    	}
    	
    	this.item = new Book();
        edit = false;
    }
    
    public void delete(Book item) throws IOException {
        // DAO save the delete
        list.remove(item);
    }
    
    public List<Book> getList() {
        return list;
    }
    
    public Book getItem() {
        return this.item;
    }
    
    public boolean isEdit() {
        return this.edit;
    }
}
