"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import Container from "./Container";

export default function Footer() {
  const path = usePathname();
  if (path === "/write") {
    return <></>;
  }
  return (
    <footer className='mt-8 bg-gray-400 text-white'>
      <Container>
        <div className='flex items-center justify-between p-2 font-bold'>
          <Link
            href='/'
            className='block w-48 border-4 border-white p-2 text-center text-sm font-bold uppercase'
          >
            flavour 4 Favor
          </Link>
          <p>Copyright © 2023 - Zolin Friendship</p>
        </div>
      </Container>
    </footer>
  );
}
