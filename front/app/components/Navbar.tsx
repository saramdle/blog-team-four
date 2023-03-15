"use client";

import { useState } from "react";
import LoginProfile from "./LoggedinProfile";
import Container from "./Container";
import Link from "next/link";
import { NAV_ITEMS } from "@/constants/navItems";
import { AiOutlineClose, AiOutlineMenu } from "react-icons/ai";

export default function Navbar() {
  const [isLoggedin, setIsLoggedin] = useState<boolean>(false);
  const login = () => setIsLoggedin(true);
  const logout = () => setIsLoggedin(false);

  // mobile drawer
  const [openDrawer, setOpenDrawer] = useState<boolean>(false);
  const handleDrawer = (): void => {
    setOpenDrawer((prev) => !prev);
    //to prevent scrolling when drawer is open
    document.body.classList.toggle("drawer-open");
  };

  return (
    <header className='select-none p-2'>
      <Container>
        <div className='flex items-center justify-between px-2'>
          <div className='hidden flex-1 gap-5 sm:flex'>
            {NAV_ITEMS.map((item) => (
              <Link
                href={item.href}
                key={item.href}
                className='btn-ghost btn transition-colors duration-300 hover:text-primary'
              >
                {item.name}
              </Link>
            ))}
          </div>
          <Link
            href='/'
            className='border-4 border-neutral p-2 text-center text-sm font-bold uppercase text-neutral transition-colors duration-300 ease-in-out hover:border-primary sm:flex-1 sm:text-3xl'
          >
            flavour 4 Favor
          </Link>
          <div className='flex flex-1 items-center'>
            <div className='ml-auto'>
              {isLoggedin ? (
                <LoginProfile logout={logout} />
              ) : (
                <div
                  onClick={login}
                  className='btn-ghost btn cursor-pointer transition-colors duration-300 hover:text-primary'
                >
                  Login
                </div>
              )}
            </div>
          </div>

          {/* mobile */}
          <div className='z-20 pl-4 sm:hidden'>
            {openDrawer ? (
              <AiOutlineClose
                size={20}
                onClick={handleDrawer}
                className='z-50 text-white'
              />
            ) : (
              <AiOutlineMenu size={20} onClick={handleDrawer} />
            )}
          </div>
          <div
            className={
              openDrawer
                ? "absolute inset-0 z-20 flex h-screen w-full items-center justify-center bg-black/90 opacity-100 transition-opacity duration-500 ease-in-out sm:hidden"
                : "opacity-0 transition-opacity duration-500 ease-in-out"
            }
            onClick={handleDrawer}
          >
            <nav
              className={`${
                openDrawer ? "" : "hidden"
              } flex flex-col items-center gap-10`}
            >
              {NAV_ITEMS.map((item) => (
                <Link
                  key={item.href}
                  href={item.href}
                  className='text-3xl font-bold text-white hover:text-primary'
                >
                  {item.name}
                </Link>
              ))}
            </nav>
          </div>
        </div>
      </Container>
    </header>
  );
}
