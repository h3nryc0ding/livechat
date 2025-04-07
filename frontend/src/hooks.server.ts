import type { HandleFetch, Handle } from '@sveltejs/kit';
import { redirect } from '@sveltejs/kit';
import { loginUrl } from '$lib/urls';

const COOKIE_NAME = 'SESSION';
const protectedPaths = new Set(['/account']);

export const handle: Handle = async ({ event, resolve }) => {
	const { cookies, locals } = event;
	locals.isAuthenticated = !!cookies.get(COOKIE_NAME);
	if (event.url.pathname in protectedPaths && !locals.isAuthenticated) {
		throw redirect(302, loginUrl());
	}
	return resolve(event);
};

export const handleFetch: HandleFetch = async ({ event, request, fetch }) => {
	// TODO: validate URL before adding cookie
	const cookie = event.request.headers.get('cookie');
	if (cookie) request.headers.set('cookie', cookie);
	return await fetch(request);
};
