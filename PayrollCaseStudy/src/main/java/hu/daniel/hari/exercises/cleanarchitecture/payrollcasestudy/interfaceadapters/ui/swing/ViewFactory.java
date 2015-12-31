package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.components.statusbar.StatusBarController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.components.statusbar.StatusBarView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.MainFrameController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.MainFrameView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.dialog.ErrorDialogController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.dialog.ErrorDialogView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.EmployeeManagerView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.dialog.AddEmployeeDialogController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.dialog.AddEmployeeDialogView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.table.EmployeesTableController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.table.EmployeesTableView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class ViewFactory {

	private UseCaseFactory useCaseFactory;
	private EventBus eventBus;

	public ViewFactory(UseCaseFactory useCaseFactory, EventBus eventBus) {
		this.useCaseFactory = useCaseFactory;
		this.eventBus = eventBus;
	}

	public EmployeeManagerView employeeManagerView() {
		EmployeeManagerView view = new EmployeeManagerView(this);
		new EmployeeManagerController(view, useCaseFactory, eventBus);
		return view;
	}
	public EmployeesTableView employeesTableView() {
		EmployeesTableView view = new EmployeesTableView(eventBus);
		new EmployeesTableController(view, useCaseFactory, eventBus);
		return view;
	}
	
	public MainFrameView mainFrameView() {
		MainFrameView mainFrameView = new MainFrameView(this);
		new MainFrameController(mainFrameView, this);
		return mainFrameView;
	}

	public AddEmployeeDialogView addEmployeeDialogView() {
		AddEmployeeDialogView dialog = new AddEmployeeDialogView();
		new AddEmployeeDialogController(dialog, useCaseFactory, eventBus);
		return dialog;
	}

	public StatusBarView statusBarView() {
		StatusBarView statusBarView = new StatusBarView();
		new StatusBarController(statusBarView, eventBus);
		return statusBarView;
	}

	public ErrorDialogView errorDialogView(Throwable e) {
		ErrorDialogView dialog = new ErrorDialogView();
		new ErrorDialogController(dialog, e);
		return dialog;
	}

	
	
}
