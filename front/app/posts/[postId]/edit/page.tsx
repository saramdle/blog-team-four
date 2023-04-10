"use client";

import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import axios, { AxiosError } from "axios";
import { usePathname, useRouter } from "next/navigation";
import { useEffect, useState, useMemo } from "react";
import { toast } from "react-hot-toast";
import "react-quill/dist/quill.snow.css";

export default function EditPost() {
  let postCommetnId: string;

  const client = useQueryClient();

  const router = useRouter();

  const pathName = usePathname();
  const postId = pathName.split("/")[2];

  const getPost = () => axios.get(`http://localhost:4000/posts/${postId}`);
  const { data } = useQuery({
    queryKey: [`posts/${postId}`, "posts"],
    queryFn: getPost,
  });
  const post: Post = data?.data;

  const [contentsInput, setContentsInput] = useState<string>(post?.contents);
  const [title, setTitle] = useState<string>(post?.title);

  // quill hydration error
  const [mounted, setMounted] = useState(false);
  useEffect(() => {
    setMounted(true);
  }, []);

  //react quill
  const ReactQuill =
    typeof window === "object"
      ? require("react-quill")
      : () => <p>Loading ...</p>;

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

  const { mutate: mutateUpdateComment } = useMutation({
    mutationFn: async (editedPost: any) => {
      await axios.put(`http://localhost:4000/posts/${postId}`, editedPost);
    },
    onSuccess: () => {
      toast.success("게시물을 수정하였습니다.", { id: postCommetnId });
    },
    onError: (error) => {
      if (error instanceof AxiosError) {
        toast.error("게시물 수정 중 에러 발생", { id: postCommetnId });
      }
    },
    onSettled: () => {
      client.invalidateQueries([`post/${postId}`]);
      router.push(`/posts/${postId}`);
    },
  });

  const handleUpdate = () => {
    const editedPost = {
      title,
      author: post?.author,
      contents: contentsInput,
      imgUrl: post?.imgUrl,
      createdAt: post?.createdAt,
      updatedAt: new Date(),
    };
    mutateUpdateComment(editedPost);
  };

  const onSubmitPreventDefault = (e: React.FormEvent) => {
    e.preventDefault();
  };

  return (
    <div className='flex'>
      <form
        className='flex flex-1 flex-col gap-5 p-4'
        onSubmit={onSubmitPreventDefault}
      >
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
          <button
            className='btn-primary btn mt-10 w-max'
            onClick={handleUpdate}
          >
            수정
          </button>
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
