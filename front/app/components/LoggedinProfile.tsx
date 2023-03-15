"use client";

import Image from "next/image";
import Link from "next/link";
import { useState } from "react";

type Props = {
  logout: () => void;
};

export default function LoginProfile({ logout }: Props) {
  const [openDropdown, setOpenDropdown] = useState(false);
  const hideDropdown = () => {
    setOpenDropdown(false);
  };
  const showDropdown = () => {
    setOpenDropdown(true);
  };
  return (
    <div className='dropdown dropdown-left flex items-center justify-center'>
      <label
        tabIndex={0}
        className='btn-ghost btn-circle avatar btn'
        onClick={showDropdown}
      >
        <div>
          <Image
            alt='dummy_profile'
            src='/dummy_profile.avif'
            width={40}
            height={40}
            className='rounded-full'
          />
        </div>
      </label>
      <ul
        tabIndex={0}
        className={`${
          openDropdown ? "" : "hidden"
        } dropdown-content menu rounded-box menu-compact mt-3  w-52 bg-base-300 p-2 shadow-2xl`}
      >
        <li>
          <Link href='/write' onClick={hideDropdown}>
            글 작성하기
          </Link>
        </li>
        <li>
          <Link href='/myPosts' onClick={hideDropdown}>
            내가 작성한 포스트
          </Link>
        </li>
        <li>
          <button
            onClick={() => {
              logout();
              hideDropdown();
            }}
          >
            로그아웃
          </button>
        </li>
      </ul>
    </div>
  );
}
