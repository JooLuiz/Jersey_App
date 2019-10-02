package com.example.state;

import com.example.models.Usuario;

public interface IState<T> {
	public T setState(T item);
	public T getState();
}
