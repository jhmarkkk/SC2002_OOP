package controllers;

abstract class AbstractUserController {
	
	public abstract void start();
	
	protected abstract void viewProfile();
	
	protected abstract void viewAllCamps();
	
	protected abstract void viewEnquiries();
	
	protected void changePassword() {
		
	}
}
