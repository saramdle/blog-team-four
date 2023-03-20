"use client";

import { useRouter } from "next/navigation";
import { useEffect, useRef, useState, useMemo } from "react";
import "react-quill/dist/quill.snow.css";

export default function Write() {
  const router = useRouter();

  //hydration error
  const [mounted, setMounted] = useState(false);
  useEffect(() => {
    setMounted(true);
  }, []);

  //react quill
  const ReactQuill =
    typeof window === "object"
      ? require("react-quill")
      : () => <p>Loading ...</p>;

  const [contentsInput, setContentsInput] = useState<string>("");
  const [title, setTitle] = useState<string>("");

  const modules = useMemo(
    () => ({
      toolbar: {
        container: [
          [{ header: 1 }, { header: 2 }],
          ["bold", "italic", "underline", "strike", "blockquote"],
          [{ size: ["small", false, "large", "huge"] }, { color: [] }],
          [{ list: "ordered" }, { list: "bullet" }],
          ["image"],
        ],
        handlers: {
          //이미지 업로드 함수 필요
          // image: imageHandler,
        },
      },
    }),
    []
  );

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const postingData = {
      title,

      // 로그인된 user로 대체될 예정
      author: "홍길동",
      contents: contentsInput,

      // 업로드된 사진 url로 대체될 예정
      imgUrl:
        "https://images.unsplash.com/photo-1606787366850-de6330128bfc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80",

      // db에서 자동생성될 예정
      createdAt: new Date(),
      updatedAt: new Date(),
    };
    const response = await fetch("http://localhost:4000/posts", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(postingData),
    });
    return response.json();
  };

  return (
    <div className='flex'>
      <form className='flex flex-1 flex-col gap-5 p-4' onSubmit={handleSubmit}>
        <div className='flex items-center'>
          <input
            type='text'
            placeholder='제목을 입력하세요'
            className='text-bold w-full bg-transparent text-4xl focus:outline-none'
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>

        <div>
          {mounted && (
            <ReactQuill
              theme='snow'
              value={contentsInput}
              onChange={setContentsInput}
              className='h-[550px]'
              modules={modules}
              placeholder='배부르지 않으면 세상 모든 것이 어렵다...'
            />
          )}
        </div>
        <div className='flex justify-between'>
          <div
            className='btn-outline btn mt-10 w-max'
            onClick={() => router.back()}
          >
            나가기
          </div>
          <button className='btn-primary btn mt-10 w-max'>발행</button>
        </div>
      </form>
      <div className='flex-1 border-l-2 p-3'>
        <h1 className='text-4xl'>{title}</h1>
        <p
          dangerouslySetInnerHTML={{ __html: contentsInput }}
          className='prose mt-4 max-w-none prose-p:m-0'
        />
      </div>
    </div>
  );
}
