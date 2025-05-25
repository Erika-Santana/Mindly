package controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {

	public String execute(HttpServletRequest req, HttpServletResponse resp);
}
